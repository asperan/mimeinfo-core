/*
Copyright (c) 2022, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.mimeinfo.mime

import io.github.asperan.helper.builder.AbstractBuilder
import io.github.asperan.helper.defaultPriority

class TreeMagic(
    private val treeMatches: List<TreeMatch>,
    private val priority: UByte,
) : ElementWithCompoundChildren() {
    override val elementName: String get() = "treemagic"
    override val attributesString: String get() = "priority=\"${this.priority}\""

    override fun getChildrenString(indentLevel: UInt, indentString: String): String =
        this.treeMatches.joinToString("\n") { it.toXmlString(indentLevel, indentString) }

    class Builder : AbstractBuilder<TreeMagic, Builder>() {
        private var treeMatches: List<TreeMatch> = listOf()
        private var priority: UByte = defaultPriority

        val addTreeMatch = builderMethod<TreeMatch> { this.treeMatches += it }
        val setPriority = builderMethod<UByte> { this.priority = it }

        override fun build(): TreeMagic = TreeMagic(this.treeMatches, this.priority)
    }
}