/*
 * Copyright 2022 Katium Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
