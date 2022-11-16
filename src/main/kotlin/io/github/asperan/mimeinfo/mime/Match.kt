/*
Copyright (c) 2022, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.mimeinfo.mime

import io.github.asperan.helper.formatOrEmptyString

/**
 * The `match` element.
 */
class Match(
    private val type: Type,
    private val offset: Offset,
    private val value: String,
    private val mask: String? = null,
) : ClosedElement() {
    override val elementName: String get() = "match"
    override val attributesString: String get() =
        "type=\"${this.type}\" offset=\"${this.offset}\" value=\"${this.value}\"" +
                this.mask.formatOrEmptyString { " mask=\"${it}\"" }

    /**
     * The offset of the match.
     */
    data class Offset(
        private val start: UInt,
        private val end: UInt? = null,
    ) {
        override fun toString(): String = "${this.start}" + this.end.formatOrEmptyString { ":${it}" }
    }

    /**
     * The type of the match. The possible types are defined in the shared MIME system documentation.
     */
    enum class Type {
        STRING, HOST16, HOST32, BIG16, BIG32, LITTLE16, LITTLE32, BYTE;
        override fun toString(): String = this.name.lowercase()
    }
}
