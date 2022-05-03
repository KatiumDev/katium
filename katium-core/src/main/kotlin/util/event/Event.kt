package katium.core.util.event

interface Event {

    val intercepted: Boolean

    fun intercept()

}