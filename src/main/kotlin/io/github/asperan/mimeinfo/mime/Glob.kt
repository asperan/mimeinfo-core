/*
Copyright (c) 2023, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.mimeinfo.mime

import io.github.asperan.helper.formatOrEmptyString

/**
 * The `glob` element.
 */
class Glob(
    private val pattern: String,
    private val weight: UByte? = null,
) : ClosedElement() {
    override val elementName: String get() = "glob"
    override val attributesString: String get() =
        "pattern=\"${this.pattern}\"${this.weight.formatOrEmptyString { " weight=\"${it}\"" }}"
}
