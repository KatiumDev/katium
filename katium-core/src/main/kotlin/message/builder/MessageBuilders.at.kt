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
@file:Suppress("FunctionName") @file:JvmName("Ats")

package katium.core.message.builder

import katium.core.chat.Chat
import katium.core.chat.ChatInfo
import katium.core.chat.GlobalChatID
import katium.core.chat.LocalChatID
import katium.core.message.content.At
import katium.core.message.content.AtAll
import katium.core.message.content.MessageContent
import katium.core.user.UserInfo

@JvmName("of")
fun At(target: LocalChatID) = At(target)

@JvmName("of")
fun At(target: GlobalChatID) = At(target.localID)

@JvmName("of")
fun At(target: ChatInfo) = At(target.localID)

@JvmName("of")
fun At(target: Chat) = At(target.contextAsUser)

@JvmName("of")
fun At(target: String) = At(LocalChatID(target))

@JvmName("getAtAll")
fun AtAll() = AtAll

operator fun MessageContent.plus(target: LocalChatID) = merge(At(target))
operator fun MessageContent.plus(target: GlobalChatID) = merge(At(target))
operator fun MessageContent.plus(target: UserInfo) = merge(At(target))
operator fun MessageContent.plus(target: Chat) = merge(At(target))
