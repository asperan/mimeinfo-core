/*
Copyright (c) 2022, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.helper

/**
 * Utility method to format a value of type T? as a string. It uses the given block if the value is not null,
 * else it returns an empty string.
 *
 * @param T The nullable type of the parameter.
 * @param formatter the formatting function to use when the value is not null.
 *
 * @return A string: formatted if the receiver is not null, else empty.
 */
internal inline fun <reified T> T?.formatOrEmptyString(formatter: (T) -> String) = when(this) {
    is T -> formatter(this)
    else -> ""
}

/**
 * Utility method to convert a nullable object in a list.
 *
 * The returned list has a single value (i.e. the object itself) if the object is not null,
 * else the returned list will be empty.
 *
 * @param T The object type, usually inferred.
 *
 * @return A list: containing the receiver if not null, else empty.
 */
internal inline fun <reified T> T?.asList(): List<T> = when(this) {
    is T -> listOf(this)
    else -> listOf()
}
