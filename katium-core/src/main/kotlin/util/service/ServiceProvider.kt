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

/**
 * Dynamic service provider.
 *
 * Dynamic providers can only be registered through `META-INF/services` classpath scanning.
 *
 * Providers in `META-INF/services/providers/(service name)` will be called for the given service.
 * Providers in `META-INF/services/providers/katium.core.service.ServiceProvider` will be called for all kinds of services.
 *
 * @see ClassPathProvider Builtin provider
 */
interface ServiceProvider {

    fun <T : Service> lookup(type: KClass<T>): Set<T>

    companion object {

        @JvmStatic
        fun lookup(
            type: KClass<out Service>,
            classLoader: ClassLoader = type.java.classLoader
        ): Set<ServiceProvider> =
            ClassPathProvider.lookup(
                ServiceProvider::class,
                classLoader,
                "providers/katium.core.service.ServiceProvider"
            )
                .toMutableSet()
                .also {
                    it.addAll(
                        ClassPathProvider.lookup(
                            ServiceProvider::class,
                            classLoader,
                            "providers/${type.java.name}"
                        )
                    )
                }
                .toSet()

    }

}
