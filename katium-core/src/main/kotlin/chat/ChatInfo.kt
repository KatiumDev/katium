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
import katium.core.BotPlatform
import katium.core.group.GroupInfo
import katium.core.user.User
import katium.core.user.UserInfo

/**
 * Information about a chat.
 *
 * Identities a unique message context, maybe a user, a bot, or a group.
 *
 * @see UserInfo
 * @see GroupInfo
 */
interface ChatInfo: ChatScope {

    /**
     * The bot which retrieved this chat.
     */
    val bot: Bot

    /**
     * The platform of this chat.
     *
     * @see Bot.platform
     */
    val platform: BotPlatform get() = bot.platform

    /**
     * The unique local identifier of this chat.
     */
    val localID: LocalChatID

    /**
     * The unique global identifier of this chat.
     */
    val globalID: GlobalChatID

    /**
     * The display name of this chat.
     */
    val name: String

}