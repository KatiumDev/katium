package katium.core.util.okhttp

import io.netty.buffer.ByteBuf
import katium.core.util.netty.toArray
import okhttp3.MediaType
import okhttp3.RequestBody.Companion.toRequestBody

fun ByteBuf.toRequestBody(release: Boolean, contentType: MediaType? = null) =
    toArray(release = release).toRequestBody(contentType = contentType)
