package katium.core.util.netty

import io.netty.buffer.ByteBuf

fun ByteBuf.use(block: (ByteBuf) -> Unit) {
    try {
        block(this)
    } finally {
        release()
    }
}
