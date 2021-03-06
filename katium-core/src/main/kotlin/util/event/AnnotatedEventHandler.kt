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

import java.util.*
import kotlin.reflect.KFunction
import kotlin.reflect.full.callSuspend

class AnnotatedEventHandler<T : Event>(
    override val priority: Int,
    override val asyncMode: AsyncMode,
    val sourceListener: EventListener,
    val sourceFunction: KFunction<*>
) : EventHandler<T> {

    override suspend fun handle(event: T) {
        if (sourceFunction.isSuspend) {
            sourceFunction.callSuspend(sourceListener, event)
        } else {
            sourceFunction.call(sourceListener, event)
        }
    }

}