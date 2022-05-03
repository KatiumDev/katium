package katium.core.util.event

abstract class BaseEvent : Event {

    private var _intercepted: Boolean = false

    override val intercepted: Boolean get() = _intercepted

    override fun intercept() {
        _intercepted = true
    }

}