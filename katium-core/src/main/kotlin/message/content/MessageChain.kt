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

open class MessageChain(vararg parts: MessageContent) : MessageContent() {

    companion object {

        @JvmField
        val EMPTY = MessageChain()

    }

    val parts: List<MessageContent> = run {
        val partList = mutableListOf<MessageContent>()
        for (part in parts) {
            if (part is MessageChain) {
                partList.addAll(part.parts)
            } else {
                partList.add(part)
            }
        }
        partList.toList()
    }

    override fun simplify() = if (parts.size == 1) parts[0] else null

    override fun concat(other: MessageContent): MessageContent? = if (other is MessageChain) {
        MessageChain(*parts.toTypedArray(), *other.parts.toTypedArray())
    } else null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MessageChain) return false
        if (!super.equals(other)) return false
        if (parts != other.parts) return false
        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + parts.hashCode()
        return result
    }

    override fun asString() = parts.joinToString(separator = "")
    override fun toString() = parts.joinToString(separator = ", ", prefix = "[", postfix = "]")

    override fun select(filter: (MessageContent) -> Boolean) = if (filter(this)) {
        MessageChain(*parts.filter(filter).toTypedArray())
    } else EMPTY

    override fun without(filter: (MessageContent) -> Boolean) = if (filter(this)) {
        MessageChain(*parts.filterNot(filter).toTypedArray())
    } else EMPTY

}