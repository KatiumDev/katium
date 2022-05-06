package katium.core.util.netty

import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufAllocator
import java.io.InputStream

inline fun ByteBufAllocator.buffer(crossinline writer: ByteBuf.() -> Unit): ByteBuf = buffer().apply(writer)
inline fun ByteBufAllocator.heapBuffer(crossinline writer: ByteBuf.() -> Unit): ByteBuf = heapBuffer().apply(writer)
inline fun ByteBufAllocator.compositeHeapBuffer(crossinline writer: ByteBuf.() -> Unit): ByteBuf = compositeHeapBuffer().apply(writer)
inline fun ByteBufAllocator.compositeBuffer(crossinline writer: ByteBuf.() -> Unit): ByteBuf = compositeBuffer().apply(writer)
inline fun ByteBufAllocator.directBuffer(crossinline writer: ByteBuf.() -> Unit): ByteBuf = directBuffer().apply(writer)
inline fun ByteBufAllocator.ioBuffer(crossinline writer: ByteBuf.() -> Unit): ByteBuf = ioBuffer().apply(writer)

fun ByteBufAllocator.buffer(bytes: ByteArray): ByteBuf = buffer(bytes.size).writeBytes(bytes)
fun ByteBufAllocator.buffer(input: InputStream): ByteBuf = buffer(input.available()).writeBytes(input.use { it.readAllBytes() })
fun ByteBufAllocator.heapBuffer(bytes: ByteArray): ByteBuf = heapBuffer(bytes.size).writeBytes(bytes)
fun ByteBufAllocator.heapBuffer(input: InputStream): ByteBuf = heapBuffer(input.available()).writeBytes(input.use { it.readAllBytes() })
fun ByteBufAllocator.compositeHeapBuffer(bytes: ByteArray): ByteBuf = compositeHeapBuffer(bytes.size).writeBytes(bytes)
fun ByteBufAllocator.compositeHeapBuffer(input: InputStream): ByteBuf = compositeHeapBuffer(input.available()).writeBytes(input.use { it.readAllBytes() })
fun ByteBufAllocator.compositeBuffer(bytes: ByteArray): ByteBuf = compositeBuffer(bytes.size).writeBytes(bytes)
fun ByteBufAllocator.compositeBuffer(input: InputStream): ByteBuf = compositeBuffer(input.available()).writeBytes(input.use { it.readAllBytes() })
fun ByteBufAllocator.directBuffer(bytes: ByteArray): ByteBuf = directBuffer(bytes.size).writeBytes(bytes)
fun ByteBufAllocator.directBuffer(input: InputStream): ByteBuf = directBuffer(input.available()).writeBytes(input.use { it.readAllBytes() })
fun ByteBufAllocator.ioBuffer(bytes: ByteArray): ByteBuf = ioBuffer(bytes.size).writeBytes(bytes)
fun ByteBufAllocator.ioBuffer(input: InputStream): ByteBuf = ioBuffer(input.available()).writeBytes(input.use { it.readAllBytes() })
