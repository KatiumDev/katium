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
package katium.core.util.service

import kotlin.reflect.KClass

open class ServiceLookup<T : Service> @JvmOverloads constructor(
    val type: KClass<T>,
    val classLoader: ClassLoader = type.java.classLoader
) {

    val providers: Set<ServiceProvider> by lazy {
        ServiceProvider.lookup(type, classLoader)
    }

    val services: Set<T> by lazy {
        providers.map { it.lookup(type) }
            .flatten()
            .sortedBy { it.priority }
            .toSet()
    }

    operator fun get(id: String): T? = services.find { it.id == id }

    fun resolve(id: String): T = get(id) ?: throw UnsupportedOperationException("No service with ID found: $id")

    fun query(id: String): T = get(id) ?: services.first()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ServiceLookup<*>) return false
        if (type != other.type) return false
        if (classLoader != other.classLoader) return false
        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + classLoader.hashCode()
        return result
    }

    override fun toString(): String {
        return "ServiceLookup($type in $classLoader)"
    }

}
