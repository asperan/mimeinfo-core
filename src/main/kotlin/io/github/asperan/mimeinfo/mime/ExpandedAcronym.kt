/*
Copyright (c) 2023, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.mimeinfo.mime

/**
 * The `expanded-acronym` element.
 */
class ExpandedAcronym(value: String, xmlLang: String? = null) : ElementWithTextualChild(value, xmlLang) {
    override val elementName: String get() = "expanded-acronym"
}
