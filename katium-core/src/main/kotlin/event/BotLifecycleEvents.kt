package katium.core.event

import katium.core.Bot
import katium.core.util.event.BaseEvent

abstract class BotEvent(val bot: Bot) : BaseEvent() {
    fun component1(): Bot = bot

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BotEvent) return false
        if (bot != other.bot) return false
        return true
    }

    override fun hashCode(): Int {
        return bot.hashCode()
    }
}

class BotOnlineEvent(bot: Bot) : BotEvent(bot) {

    override fun toString() = "BotOnlineEvent($bot)"

}

class BotOfflineEvent(bot: Bot) : BotEvent(bot) {

    override fun toString() = "BotOfflineEvent($bot)"

}
