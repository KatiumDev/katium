package katium.core.util.event

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.jvm.jvmErasure

@Suppress("OPT_IN_USAGE")
class EventBus(override val coroutineContext: CoroutineContext = GlobalScope.coroutineContext) : CoroutineScope {

    private val subscriptions = mutableMapOf<Class<out Event>, MutableList<EventHandler<out Event>>>()

    fun register(subscriber: EventListener) {
        collectSubscribeMethods(subscriber::class).forEach {
            if (it.parameters.size != 2) {
                throw IllegalArgumentException("Event handler $it has more than one parameter")
            }
            if (!it.parameters[1].type.jvmErasure.isSubclassOf(Event::class)) {
                throw IllegalArgumentException("Event handler $it has non-Event parameter")
            }
            val annotation = it.findAnnotation<Subscribe>()!!
            @Suppress("UNCHECKED_CAST")
            listen(
                it.parameters[1].type.jvmErasure as KClass<out Event>,
                AnnotatedEventHandler(
                    annotation.priority,
                    annotation.async,
                    subscriber,
                    it
                )
            )
        }
    }

    fun unregister(subscriber: EventListener) {
        collectSubscribeMethods(subscriber::class).forEach {
            if (it.parameters.size != 1) {
                throw IllegalArgumentException("Event handler $it has more than one parameter")
            }
            subscriptions[it.parameters[0].type.jvmErasure.java]
                ?.removeIf { handler -> handler is AnnotatedEventHandler && handler.sourceListener == subscriber }
        }
    }

    private fun collectSubscribeMethods(subscriber: KClass<out EventListener>) =
        subscriber.memberFunctions.filter { it.hasAnnotation<Subscribe>() }

    fun <T : Event> listen(type: KClass<T>, handler: EventHandler<T>) = listen(type.java, handler)

    fun <T : Event> listen(type: Class<T>, handler: EventHandler<T>) {
        subscriptions.getOrPut(type) { mutableListOf<EventHandler<*>>() }.apply {
            add(handler)
            sortBy { it.priority }
        }
    }

    fun <T : Event> remove(type: KClass<T>, handler: EventHandler<T>) = remove(type.java, handler)

    fun <T : Event> remove(type: Class<T>, handler: EventHandler<T>) = subscriptions[type]?.remove(handler)

    suspend fun post(event: Event) {
        var type: Class<*> = event::class.java
        while (type.superclass != null) {
            subscriptions[type]?.forEach {
                it.dispatch(this, event)
            }
            if (type == Event::class.java) {
                break
            } else {
                type = type.superclass
            }
        }
    }

    suspend fun <T : Event> await(type: KClass<T>): T = await(type.java)

    suspend fun <T : Event> await(type: Class<T>): T = suspendCoroutine {
        listen(type, object : EventHandler<T> {
            override suspend fun handle(event: T) {
                remove(type, this)
                it.resume(event)
            }
        })
    }

}