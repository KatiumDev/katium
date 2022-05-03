package katium.core.util

import io.netty.buffer.ByteBuf

// Readers

fun ByteBuf.readUByte(): UByte = readByte().toUByte()

fun ByteBuf.readUShort(): UShort = readShort().toUShort()

fun ByteBuf.readUInt(): UInt = readInt().toUInt()

fun ByteBuf.readULong(): ULong = readLong().toULong()

// Writers

fun ByteBuf.writeUByte(value: UByte): ByteBuf = writeByte(value.toInt())

@Suppress("OPT_IN_IS_NOT_ENABLED")
@OptIn(ExperimentalUnsignedTypes::class)
fun ByteBuf.writeUByteArray(value: UByteArray): ByteBuf = writeBytes(value.toByteArray())

fun ByteBuf.writeUShort(value: UShort): ByteBuf = writeShort(value.toInt())

fun ByteBuf.writeUInt(value: UInt): ByteBuf = writeInt(value.toInt())

fun ByteBuf.writeULong(value: ULong): ByteBuf = writeLong(value.toLong())

// Getters

fun ByteBuf.getUByte(index: Int): UByte = getByte(index).toUByte()

fun ByteBuf.getUShort(index: Int): UShort = getShort(index).toUShort()

fun ByteBuf.getUInt(index: Int): UInt = getInt(index).toUInt()

fun ByteBuf.getULong(index: Int): ULong = getLong(index).toULong()

// Setters

fun ByteBuf.setUByte(index: Int, value: UByte): ByteBuf = setByte(index, value.toInt())

fun ByteBuf.setUShort(index: Int, value: UShort): ByteBuf = setShort(index, value.toInt())

fun ByteBuf.setUInt(index: Int, value: UInt): ByteBuf = setInt(index, value.toInt())

fun ByteBuf.setULong(index: Int, value: ULong): ByteBuf = setLong(index, value.toLong())
