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
package katium.core.chat

import katium.core.Bot
import katium.core.group.Group
import katium.core.message.MessageRef
import katium.core.message.content.MessageContent
import katium.core.user.Contact
import katium.core.user.User

abstract class Chat(
    override val bot: Bot,
    override val localID: LocalChatID,
    val context: Chattable
) : ChatInfo, Chattable {

    override val globalID: GlobalChatID by lazy {
        GlobalChatID(platform, localID)
    }

    override val chat: Chat get() = this

    val contextUser: User? get() = if (context is User) context else null
    val contextAsUser: User get() = contextUser ?: throw IllegalStateException("$this is not a user chat")
    val contextContact: Contact? get() = contextUser?.asContact
    val contextAsContact: Contact get() = contextContact ?: throw IllegalStateException("$this is not a contact chat")
    val contextGroup: Group? get() = if (context is Group) context else null
    val contextAsGroup: Group get() = contextGroup ?: throw IllegalStateException("$this is not a group chat")

    abstract val members: Set<User>

    abstract suspend fun sendMessage(message: MessageContent): MessageRef
    abstract suspend fun removeMessage(message: MessageRef)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Chat) return false
        if (bot != other.bot) return false
        if (context != other.context) return false
        return true
    }

    override fun hashCode(): Int {
        var result = bot.hashCode()
        result = 31 * result + context.hashCode()
        return result
    }

    override fun toString() = "Chat($bot, $localID)"

}