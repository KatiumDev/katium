package katium.core.util.event

import kotlinx.coroutines.launch

@FunctionalInterface
interface EventHandler<E : Event> {

    val priority: Int get() = 10

    val asyncMode: AsyncMode get() = AsyncMode.SYNC

    val ignoreInterception: Boolean get() = false

    suspend fun handle(event: E)

    @Suppress("UNCHECKED_CAST")
    suspend fun dispatch(bus: EventBus, event: Event) {
        if (event.intercepted && !ignoreInterception) return
        when (asyncMode) {
            AsyncMode.SYNC -> handle(event as E)
            AsyncMode.ASYNC -> bus.launch { handle(event as E) }
        }
    }

}