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

import katium.core.util.service.Service
import katium.core.util.service.ServiceLookup

abstract class BotPlatform: Service {

    companion object {

        val lookup = ServiceLookup(BotPlatform::class)

    }

    abstract val fullName: String

    val shortName: String get() = id

    abstract fun createBot(config: Map<String, String>): Bot

    override fun toString() = shortName


}