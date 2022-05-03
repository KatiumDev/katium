package katium.core.message.content

open class MessageChain(vararg parts: MessageContent) : MessageContent() {

    val parts: List<MessageContent> = run {
        val partList = mutableListOf<MessageContent>()
        for (part in parts) {
            if (part is MessageChain) {
                partList.addAll(part.parts)
            } else {
                partList.add(part)
            }
        }
        partList.toList()
    }

    override fun simplify() = null

    override fun concat(other: MessageContent): MessageContent? = if (other is MessageChain) {
        MessageChain(*parts.toTypedArray(), *other.parts.toTypedArray())
    } else null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MessageChain) return false
        if (!super.equals(other)) return false
        if (parts != other.parts) return false
        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + parts.hashCode()
        return result
    }

    override fun toString() = parts.joinToString(separator = ", ", prefix = "[", postfix = "]")

}