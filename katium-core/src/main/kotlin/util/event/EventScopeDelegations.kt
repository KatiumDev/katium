package katium.core.util.event

fun EventScope.register(subscriber: EventListener) = eventBus.register(subscriber)

fun EventScope.unregister(subscriber: EventListener) = eventBus.unregister(subscriber)

suspend fun EventScope.post(event: Event) = eventBus.post(event)
