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
package katium.core.util.event

import kotlinx.coroutines.launch

@FunctionalInterface
interface EventHandler<E : Event> {

    val priority: Int get() = 10

    val asyncMode: AsyncMode get() = AsyncMode.SYNC

    val ignoreInterception: Boolean get() = false

    suspend fun handle(event: E)

    @Suppress("UNCHECKED_CAST")
    suspend fun dispatch(bus: EventBus, event: Event) {
        if (event.intercepted && !ignoreInterception) return
        when (asyncMode) {
            AsyncMode.SYNC -> handle(event as E)
            AsyncMode.ASYNC -> bus.launch { handle(event as E) }
        }
    }

}