package katium.core.util.event

import kotlin.reflect.KFunction
import kotlin.reflect.full.callSuspend

class AnnotatedEventHandler<T : Event>(
    override val priority: Int,
    override val asyncMode: AsyncMode,
    val sourceListener: EventListener,
    val sourceFunction: KFunction<*>
) : EventHandler<T> {

    override suspend fun handle(event: T) {
        if (sourceFunction.isSuspend) {
            sourceFunction.callSuspend(sourceListener, event)
        } else {
            sourceFunction.call(sourceListener, event)
        }
    }

}