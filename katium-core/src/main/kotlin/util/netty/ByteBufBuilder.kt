package katium.core.util.netty

import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufAllocator

inline fun ByteBufAllocator.buffer(crossinline writer: ByteBuf.() -> Unit): ByteBuf = buffer().apply(writer)
inline fun ByteBufAllocator.heapBuffer(crossinline writer: ByteBuf.() -> Unit): ByteBuf = heapBuffer().apply(writer)
inline fun ByteBufAllocator.compositeHeapBuffer(crossinline writer: ByteBuf.() -> Unit): ByteBuf = compositeHeapBuffer().apply(writer)
inline fun ByteBufAllocator.compositeBuffer(crossinline writer: ByteBuf.() -> Unit): ByteBuf = compositeBuffer().apply(writer)
inline fun ByteBufAllocator.directBuffer(crossinline writer: ByteBuf.() -> Unit): ByteBuf = directBuffer().apply(writer)
inline fun ByteBufAllocator.ioBuffer(crossinline writer: ByteBuf.() -> Unit): ByteBuf = ioBuffer().apply(writer)
