/*
Copyright (c) 2022, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.mimeinfo.mime

import io.github.asperan.helper.DEFAULT_PRIORITY

/**
 * The `treemagic` element.
 */
class TreeMagic(
    private val treeMatches: List<TreeMatch>,
    private val priority: UByte,
) : ElementWithCompoundChildren() {
    override val elementName: String get() = "treemagic"
    override val attributesString: String get() = "priority=\"${this.priority}\""

    override fun getChildrenString(indentLevel: UInt, indentString: String): String =
        this.treeMatches.joinToString("\n") { it.toXmlString(indentLevel, indentString) }

    /**
     * Builder of TreeMagic.
     */
    class Builder {
        private var treeMatches: List<TreeMatch> = listOf()
        private var priority: UByte = DEFAULT_PRIORITY

        /**
         * Add a TreeMatch to the TreeMagic to be built.
         *
         * @param treeMatch The TreeMatch to add.
         */
        fun addTreeMatch(treeMatch: TreeMatch) = apply { this.treeMatches += treeMatch }

        /**
         * Set the priority of the TreeMagic.
         *
         * @param priority The priority to set.
         */
        fun setPriority(priority: UByte) = apply { this.priority = priority }

        /**
         * Build the TreeMagic element.
         */
        fun build(): TreeMagic = TreeMagic(this.treeMatches, this.priority)
    }
}
