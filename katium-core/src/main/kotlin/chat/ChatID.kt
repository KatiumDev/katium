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

import katium.core.BotPlatform

open class LocalChatID(val id: String) {

    operator fun component1() = id

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LocalChatID) return false
        if (id != other.id) return false
        return true
    }

    override fun hashCode() = id.hashCode()

    override fun toString() = id

}

data class GlobalChatID(val platform: BotPlatform, val localID: LocalChatID) {

    constructor(descriptor: String) : this(
        BotPlatform.lookup.resolve(descriptor.substringBefore(":")),
        LocalChatID(descriptor.substringAfter(":"))
    )

    val descriptor = "$platform:$localID"

    override fun toString(): String = descriptor

}
