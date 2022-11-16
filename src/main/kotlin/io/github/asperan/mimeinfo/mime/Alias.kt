/*
Copyright (c) 2022, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.mimeinfo.mime

class Alias(private val type: String) : ClosedElement() {
    override val elementName: String get() = "alias"
    override val attributesString: String get() = "type=\"${this.type}\""
}