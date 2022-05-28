package katium.core.event

import katium.core.message.Message

abstract class MessageEvent(var message: Message) : BotEvent(message.bot) {

    operator fun component2(): Message = message

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MessageEvent) return false
        if (message != other.message) return false
        return true
    }

    override fun hashCode() = message.hashCode()

}

class MessageReceivedEvent(message: Message) : MessageEvent(message) {

    override fun toString() = "MessageReceivedEvent(message=$message)"

}

class MessagePreSendEvent(message: Message) : MessageEvent(message) {

    override fun toString() = "MessagePreSendEvent(message=$message)"

}

class MessageSentEvent(message: Message) : MessageEvent(message) {

    override fun toString() = "MessageSentEvent(message=$message)"

}
