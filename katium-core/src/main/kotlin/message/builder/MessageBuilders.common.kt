@file:JvmName("Messages")

package katium.core.message.builder

import katium.core.message.content.MessageContent
import katium.core.message.content.PlainText

operator fun MessageContent.plus(other: MessageContent) = merge(other)

operator fun MessageContent.plus(text: String) = merge(PlainText(text))
