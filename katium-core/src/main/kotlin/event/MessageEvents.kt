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
