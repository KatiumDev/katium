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
package katium.core.user

import katium.core.Bot
import katium.core.chat.Chat
import katium.core.chat.GlobalChatID
import katium.core.chat.LocalChatID

abstract class User(override val bot: Bot, override val localID: LocalChatID) : UserInfo {

    override val globalID: GlobalChatID by lazy {
        GlobalChatID(platform, localID)
    }

    abstract val asContact: Contact?

    override val chat: Chat? get() = asContact?.chat

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false
        if (bot != other.bot) return false
        if (localID != other.localID) return false
        return true
    }

    override fun hashCode(): Int {
        var result = bot.hashCode()
        result = 31 * result + localID.hashCode()
        return result
    }

    override fun toString() = "User($bot, $localID, $name)"

}