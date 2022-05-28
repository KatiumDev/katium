package katium.core.event

import katium.core.Bot
import katium.core.chat.Chat
import katium.core.message.Message
import katium.core.message.content.MessageContent

abstract class MessageEvent(val message: Message) : BotEvent(message.bot) {

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

class MessagePreSendEvent(bot: Bot, val context: Chat, var content: MessageContent) : BotEvent(bot) {

    override fun toString() = "MessagePreSendEvent(context=$context, content=$content)"

}

class MessageSentEvent(message: Message) : MessageEvent(message) {

    override fun toString() = "MessageSentEvent(message=$message)"

}
