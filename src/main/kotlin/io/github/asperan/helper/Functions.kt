/*
Copyright (c) 2022, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.helper

/**
 * Compute the prefix string for indentation given a level and a string to use.
 *
 * @param indentLevel the level of indentation.
 * @param indentString the string to use for indentation.
 *
 * @return The indentation string to prefix.
 */
internal fun computeIndentPrefix(indentLevel: UInt, indentString: String): String =
    indentString.repeat(indentLevel.toInt())
