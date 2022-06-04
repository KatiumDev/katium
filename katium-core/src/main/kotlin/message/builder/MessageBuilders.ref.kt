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
@file:Suppress("FunctionName")
@file:JvmName("Refs")

package katium.core.message.builder

import katium.core.message.Message
import katium.core.message.MessageRef
import katium.core.message.content.MessageContent
import katium.core.message.content.QuoteReply
import katium.core.message.content.RefMessage

@JvmName("ofRef")
fun Ref(ref: MessageRef) = RefMessage(ref)

@JvmName("ofRef")
fun Ref(message: Message) = RefMessage(message.ref)

@JvmName("ofReply")
fun Reply(ref: MessageRef) = QuoteReply(ref)

@JvmName("ofReply")
fun Reply(message: Message) = QuoteReply(message.ref)

operator fun MessageContent.plus(ref: MessageRef) = merge(RefMessage(ref))
operator fun MessageContent.plus(message: Message) = merge(Reply(message))
