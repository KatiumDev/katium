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

open class PlainText(val text: String) : MessageContent() {

    override fun simplify() = null

    override fun concat(other: MessageContent) = if (other is PlainText) {
        PlainText(text + other.text)
    } else null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PlainText) return false
        if (text != other.text) return false
        return true
    }

    override fun hashCode() = text.hashCode()

    override fun toString() = "\"$text\""

}