package katium.core.util.event

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Subscribe(val priority: Int = 10, val async: AsyncMode = AsyncMode.SYNC)
