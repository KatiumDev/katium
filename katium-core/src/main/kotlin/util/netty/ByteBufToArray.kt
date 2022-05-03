package katium.core.util.netty

import io.netty.buffer.ByteBuf

fun ByteBuf.toArray(release: Boolean, size: Int = readableBytes()): ByteArray {
    val array = ByteArray(size)
    this.readBytes(array)
    if (release) {
        this.release()
    }
    return array
}