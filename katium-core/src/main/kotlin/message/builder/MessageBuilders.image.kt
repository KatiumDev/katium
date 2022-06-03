@file:JvmName("ImageMessages")

package katium.core.message.builder

import katium.core.message.content.Image
import katium.core.message.content.MessageContent
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import java.net.URI
import java.net.URL
import javax.imageio.ImageIO

@Suppress("FunctionName")
fun Image(data: ByteArray, width: Int? = null, height: Int? = null) = Image.ImageWithContent(data, width, height)

@Suppress("FunctionName")
fun Image(url: String, width: Int? = null, height: Int? = null) = Image.ImageWithUrl(url, width, height)

@Suppress("FunctionName")
fun Image(url: URL, width: Int? = null, height: Int? = null) = Image(url.toString(), width, height)

@Suppress("FunctionName")
fun Image(url: URI, width: Int? = null, height: Int? = null) = Image(url.toURL(), width, height)

@Suppress("FunctionName")
fun Image(file: File, width: Int? = null, height: Int? = null) = Image(file.readBytes(), width, height)

operator fun MessageContent.plus(image: BufferedImage) =
    merge(Image(ByteArrayOutputStream().apply {
        if (!ImageIO.write(image, "PNG", this))
            throw UnsupportedOperationException("Unable to encode BufferedImage")
    }.toByteArray(), image.width, image.height))
