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
package katium.core.message.content

abstract class MessageContent {

    abstract fun simplify(): MessageContent?

    val simplest by lazy { simplify() ?: this }

    abstract fun concat(other: MessageContent): MessageContent?

    open fun merge(other: MessageContent): MessageContent = concat(other) ?: MessageChain(this, other)

    open fun asString() = toString()
    abstract override fun toString(): String

    open fun select(filter: (MessageContent) -> Boolean) = if (filter(this)) this else MessageChain.EMPTY
    open fun without(filter: (MessageContent) -> Boolean) = if (filter(this)) MessageChain.EMPTY else this

}