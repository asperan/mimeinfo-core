/*
Copyright (c) 2023, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.mimeinfo.mime

/**
 * The `sub-class-of` element.
 */
class SubClassOf(
    private val type: String,
) : ClosedElement() {
    override val elementName: String get() = "sub-class-of"
    override val attributesString: String get() = "type=\"${this.type}\""
}
