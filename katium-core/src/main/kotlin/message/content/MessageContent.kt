package katium.core.message.content

abstract class MessageContent {

    abstract fun simplify(): MessageContent?

    abstract fun concat(other: MessageContent): MessageContent?

    open fun merge(other: MessageContent): MessageContent = concat(other) ?: MessageChain(this, other)

    abstract override fun toString(): String

    operator fun plus(other: MessageContent): MessageContent = merge(other)

}