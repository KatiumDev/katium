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
import katium.core.message.content.PlainText
import katium.core.user.Contact
import katium.core.user.User

abstract class Chat(
    override val bot: Bot,
    override val localID: LocalChatID,
    val context: ChatInfo
) : ChatInfo by context {

    override val chat: Chat get() = this

    @Suppress("LeakingThis")
    open val members: Set<User> by lazy {
        if (contextUser != null) setOf(contextAsUser, bot.selfInfo)
        else if (contextGroup != null) contextAsGroup.members
        else throw IllegalStateException("Unsupported context type")
    }

    open val contextUser: User? get() = if (context is User) context else if (context is Contact) context.asUser else null
    val contextAsUser: User get() = contextUser ?: throw IllegalStateException("$this is not a user chat")
    open val contextContact: Contact? get() = if (context is User) context.asContact else if (context is Contact) context else null
    val contextAsContact: Contact get() = contextContact ?: throw IllegalStateException("$this is not a contact chat")
    open val contextGroup: Group? get() = if (context is Group) context else null
    val contextAsGroup: Group get() = contextGroup ?: throw IllegalStateException("$this is not a group chat")

    abstract suspend fun sendMessage(content: MessageContent): MessageRef?
    abstract suspend fun removeMessage(ref: MessageRef)

    suspend operator fun MessageContent.unaryPlus() = sendMessage(this)
    suspend operator fun String.unaryPlus() = sendMessage(PlainText(this))
    suspend operator fun MessageRef.unaryMinus() = removeMessage(this)

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

    override fun toString() = "Chat($bot, $localID, $name)"

}