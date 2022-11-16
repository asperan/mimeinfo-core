/*
Copyright (c) 2022, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.helper.builder

/**
 * Builder class with a helper method to create building methods.
 *
 * It uses a self type to allow the concatenation of methods of the specific class.
 *
 * Example usage:
 * ```
 * class FooBuilder : AbstractBuilder<Foo, FooBuilder>() [...]
 * ```
 *
 * @param B The type of the builder which the builder methods will return.
 * @param T The type built.
 */
abstract class AbstractBuilder<T, B : AbstractBuilder<T, B>> {

    /**
     * Helper method. It allows the definition of lambdas to use to construct a builder.
     *
     * Example:
     * ```
     * class FooBuilder : AbstractBuilder<Foo, FooBuilder>() {
     *     private var fooValue: String
     *
     *     val setValue = builderMethod<String> { this.fooValue = it }
     * }
     * ```
     *
     * @param S The type of the value to consume.
     * @param consumer The consuming function.
     *
     * @return The builder itself.
     */
    protected fun <S> builderMethod(consumer: (S) -> Unit): (S) -> B = {
        consumer(it)
        @Suppress("UNCHECKED_CAST")
        this as B
    }

    /**
     * Build a new object of type T.
     *
     * @throws BuilderStateException if coded and when the builder state is not ready to build an object.
     *
     * @return a new T object.
     */
    abstract fun build(): T
}
