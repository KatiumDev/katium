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
@file:JvmName("Images")

package katium.core.message.builder

import katium.core.message.content.Image
import katium.core.message.content.MessageContent
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import java.net.URI
import java.net.URL
import javax.imageio.ImageIO

@JvmName("of")
@JvmOverloads
fun Image(data: ByteArray, width: Int? = null, height: Int? = null) = Image.ImageWithContent(data, width, height)

@JvmName("of")
@JvmOverloads
fun Image(url: String, width: Int? = null, height: Int? = null) = Image.ImageWithUrl(url, width, height)

@JvmName("of")
@JvmOverloads
fun Image(url: URL, width: Int? = null, height: Int? = null) = Image(url.toString(), width, height)

@JvmName("of")
@JvmOverloads
fun Image(url: URI, width: Int? = null, height: Int? = null) = Image(url.toURL(), width, height)

@JvmName("of")
@JvmOverloads
fun Image(file: File, width: Int? = null, height: Int? = null) = Image(file.readBytes(), width, height)

@JvmName("of")
@JvmOverloads
fun Image(image: BufferedImage, width: Int? = image.width, height: Int? = image.height) =
    Image(ByteArrayOutputStream().apply {
        if (!ImageIO.write(image, "PNG", this))
            throw UnsupportedOperationException("Unable to encode BufferedImage")
    }.toByteArray(), width, height)

operator fun MessageContent.plus(image: BufferedImage) = merge(Image(image))
