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

import java.net.URL
import kotlin.reflect.KClass

/**
 * Service provider using class-path scanning.
 *
 * This provider scans `META-INF/services/(service name)` with the given class-loader(class-loader of the service class by default).
 *
 * Each line of the file should be a fully qualified class name of a service implementation, an empty line, or comment lines starts with `#`.
 *
 * Supports Kotlin `object`s and Java classes with `INSTANCE` static field.
 */
class ClassPathProvider : ServiceProvider {

    override fun <T : Service> lookup(type: KClass<T>) = Companion.lookup(type)

    companion object {

        @JvmStatic
        fun <T : Any> lookup(
            type: KClass<T>,
            classLoader: ClassLoader = type.java.classLoader,
            path: String = type.java.name
        ): Set<T> {
            return classLoader.getResources("META-INF/services/$path")
                .asSequence()
                .map(URL::openConnection)
                .onEach { it.useCaches = false }
                .map { it.getInputStream().use { inputStream -> inputStream.bufferedReader().lines().toList() } }
                .flatten()
                .map(String::trim)
                .filterNot { it.startsWith("#", ignoreCase = true) }
                .filterNot(String::isEmpty)
                .map(classLoader::loadClass)
                .map(Class<*>::kotlin)
                .map {
                    @Suppress("UNCHECKED_CAST")
                    it as KClass<out T>
                }
                .map {
                    if (it.objectInstance != null) {
                        it.objectInstance as T
                    }
                    try {
                        @Suppress("UNCHECKED_CAST")
                        it.java.getDeclaredField("INSTANCE").get(null) as T
                    } catch (e: NoSuchFieldException) {
                        it.constructors.find { cons -> cons.parameters.isEmpty() }!!.call()
                    }
                }
                .toSet()
        }

    }

}