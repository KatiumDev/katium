package katium.core.message.content

open class PlainTextMessage(val text: String) : MessageContent() {

    override fun simplify() = null

    override fun concat(other: MessageContent) = if (other is PlainTextMessage) {
        PlainTextMessage(text + other.text)
    } else null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PlainTextMessage) return false
        if (text != other.text) return false
        return true
    }

    override fun hashCode(): Int {
        return text.hashCode()
    }

    override fun toString() = "\"$text\""

}