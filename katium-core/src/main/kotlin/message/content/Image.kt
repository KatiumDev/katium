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

abstract class Image(val width: Int? = null, val height: Int? = null) : MessageContent() {

    abstract val contentUrl: String?
    abstract val contentBytes: ByteArray?

    override fun simplify() = null

    override fun concat(other: MessageContent) = null

    override fun toString() = "[Image]"

    open class ImageWithContent internal constructor(
        override val contentBytes: ByteArray,
        width: Int? = null,
        height: Int? = null
    ) : Image(width, height) {

        override val contentUrl: String?
            get() = null

    }

}

@Suppress("FunctionName")
fun Image(data: ByteArray, width: Int? = null, height: Int? = null) = Image.ImageWithContent(data, width, height)
