package katium.core.util.event

/**
 * Synchronization mode for event handlers.
 *
 * @see EventHandler.asyncMode
 * @see EventHandler.dispatch
 */
enum class AsyncMode {

    /**
     * The handler will be called in the thread which post the event.
     */
    SYNC,

    /**
     * The handler will be called in a new coroutine job using `launch`.
     *
     * Event should not get intercepted in async handlers, else interception may be applied incompletely.
     */
    ASYNC;

}