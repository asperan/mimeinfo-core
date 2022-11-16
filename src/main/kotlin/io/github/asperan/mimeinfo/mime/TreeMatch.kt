/*
Copyright (c) 2022, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.mimeinfo.mime

import io.github.asperan.helper.builder.AbstractBuilder
import io.github.asperan.helper.builder.BuilderStateException
import io.github.asperan.helper.formatOrEmptyString

class TreeMatch(
    private val path: String,
    private val type: Type?,
    private val matchCase: Boolean?,
    private val isExecutable: Boolean?,
    private val nonEmpty: Boolean?,
    private val mimetype: String?,
    private val children: List<TreeMatch>,
) : ElementWithCompoundChildren() {
    override val elementName: String get() = "treematch"
    override val attributesString: String get() = "path=\"${this.path}\"" +
            this.type.formatOrEmptyString { " type=\"${it}\"" } +
            this.matchCase.formatOrEmptyString { " match-case=\"${it}\"" } +
            this.isExecutable.formatOrEmptyString { " executable=\"${it}\"" } +
            this.nonEmpty.formatOrEmptyString { " non-empty=\"${it}\"" } +
            this.mimetype.formatOrEmptyString { " mimetype=\"${it}\"" }

    override fun getChildrenString(indentLevel: UInt, indentString: String): String = this.children.joinToString("\n"){ toXmlString(indentLevel, indentString) }

    class Builder : AbstractBuilder<TreeMatch, Builder>() {
        private var path: String? = null
        private var treeMatchType: Type? = null
        private var matchCase: Boolean? = null
        private var isExecutable: Boolean? = null
        private var nonEmpty: Boolean? = null
        private var mimetype: String? = null
        private var children: List<TreeMatch> = listOf()

        val setPath = builderMethod<String> { this.path = it }
        val setTreeMatchType = builderMethod<Type> { this.treeMatchType = it }
        val setMatchCase = builderMethod<Boolean> { this.matchCase = it }
        val setIsExecutable = builderMethod<Boolean> { this.isExecutable = it }
        val setNonEmpty = builderMethod<Boolean> { this.nonEmpty = it }
        val setMimetype = builderMethod<String> { this.mimetype = it }
        val addTreeMatch = builderMethod<TreeMatch> { this.children += it }

        override fun build(): TreeMatch = when(this.path) {
            is String -> TreeMatch(this.path!!, this.treeMatchType, this.matchCase, this.isExecutable, this.nonEmpty, this.mimetype, this.children)
            else -> throw BuilderStateException()
        }
    }

    enum class Type
    {
        FILE,
        DIRECTORY,
        LINK;

        override fun toString(): String = this.name.lowercase()
    }
}
