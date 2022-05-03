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
package katium.core.message

import katium.core.Bot
import katium.core.chat.Chat
import katium.core.chat.ChatInfo
import katium.core.group.Group
import katium.core.message.content.MessageContent
import katium.core.user.Contact
import katium.core.user.User

abstract class Message(val bot: Bot, val context: Chat, val sender: ChatInfo, val content: MessageContent) {

    abstract val ref: MessageRef

    val senderUser: User? get() = if (sender is User) sender else null

    val contextUser: User? get() = context.contextUser
    val contextContact: Contact? get() = context.contextContact
    val contextGroup: Group? get() = context.contextGroup

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Message) return false
        if (ref != other.ref) return false
        return true
    }

    override fun hashCode(): Int {
        return ref.hashCode()
    }

    override fun toString(): String {
        return "Message($bot, $context, $sender: $content)"
    }

}
