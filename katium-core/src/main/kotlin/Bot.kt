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
package katium.core

import katium.core.chat.GlobalChatID
import katium.core.chat.LocalChatID
import katium.core.group.Group
import katium.core.review.ReviewMessage
import katium.core.user.Contact
import katium.core.user.User
import katium.core.util.event.EventBus
import katium.core.util.event.EventScope
import kotlinx.coroutines.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.coroutines.CoroutineContext

abstract class Bot(
    val platform: BotPlatform,
    val selfID: LocalChatID,
    val config: Map<String, String>,
) : CoroutineScope, EventScope {

    val selfGlobalID = GlobalChatID(platform, selfID)
    val logger: Logger = LoggerFactory.getLogger(selfGlobalID.descriptor)

    val selfInfo by lazy {
        getUserSync(selfID)!!
    }

    override val coroutineContext: CoroutineContext by lazy {
        CoroutineName(selfGlobalID.descriptor)
    }
    override val eventBus: EventBus by lazy {
        EventBus(coroutineContext)
    }

    abstract val loopJob: Job
    fun start() = loopJob.start()
    suspend fun join() = loopJob.join()
    abstract fun stop()

    suspend fun startAndJoin() {
        start()
        join()
    }

    val isActive get() = loopJob.isActive
    abstract val isConnected: Boolean
    abstract val isOnline: Boolean

    abstract val allContacts: Set<Contact>
    abstract val allGroups: Set<Group>

    abstract suspend fun getUser(id: LocalChatID): User?
    abstract suspend fun getGroup(id: LocalChatID): Group?

    open fun getUserSync(id: LocalChatID) = runBlocking(coroutineContext) { getUser(id) }
    open fun getGroupSync(id: LocalChatID) = runBlocking(coroutineContext) { getGroup(id) }

    abstract val reviewMessages: Set<ReviewMessage>
    open val unprocessedReviewMessages get() = reviewMessages.filter { !it.processed }
    fun getReviewMessage(id: String) = reviewMessages.find { it.id == id }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Bot) return false
        if (selfGlobalID != other.selfGlobalID) return false
        return true
    }

    override fun hashCode() = selfGlobalID.hashCode()

    override fun toString() = "Bot($selfGlobalID)"

}