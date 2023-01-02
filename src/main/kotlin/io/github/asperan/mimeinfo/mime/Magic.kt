/*
Copyright (c) 2023, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.mimeinfo.mime

import io.github.asperan.helper.DEFAULT_PRIORITY

/**
 * The `magic` element.
 */
class Magic(
    private val matches: List<Match>,
    private val priority: UByte,
) : ElementWithCompoundChildren() {
    override val elementName: String get() = "magic"
    override val attributesString: String get() = "priority=\"${this.priority}\""
    override fun getChildrenString(indentLevel: UInt, indentString: String): String =
        this.matches.joinToString("\n") { it.toXmlString(indentLevel, indentString) }

    /**
     * Builder for Magic elements.
     */
    class Builder {
        private var matches: List<Match> = listOf()
        private var priority: UByte = DEFAULT_PRIORITY

        /**
         * Adds a match to the magic specification.
         *
         * @param match The match to add to the Magic.
         */
        fun addMatch(match: Match) = apply { this.matches += match }

        /**
         * Sets the priority of the magic element.
         * @param priority The priority of the Magic.
         */
        fun setPriority(priority: UByte) = apply { this.priority = priority }

        /**
         * Build the Magic Element.
         */
        fun build(): Magic = Magic(this.matches, this.priority)
    }
}
