/*
Copyright (c) 2022, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.mimeinfo.mime

import io.github.asperan.helper.builder.BuilderStateException
import io.github.asperan.helper.formatOrEmptyString

/**
 * The `treematch` element.
 */
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

    override fun getChildrenString(indentLevel: UInt, indentString: String): String =
        this.children.joinToString("\n"){ toXmlString(indentLevel, indentString) }

    /**
     * Builder for TreeMatch.
     */
    class Builder {
        private val buildErrorMessage = "Building a TreeMatch requires a non-null path, but the given path was null."
        private var path: String? = null
        private var treeMatchType: Type? = null
        private var matchCase: Boolean? = null
        private var isExecutable: Boolean? = null
        private var nonEmpty: Boolean? = null
        private var mimetype: String? = null
        private var children: List<TreeMatch> = listOf()

        /**
         * @param path The path of the TreeMatch.
         */
        fun setPath(path: String) = apply { this.path = path }

        /**
         * @param type The Type of the TreeMatch.
         */
        fun setTreeMatchType(type: Type) = apply { this.treeMatchType = type }

        /**
         * @param enabled Whether to enable case matching in the TreeMatch.
         */
        fun setMatchCase(enabled: Boolean) = apply { this.matchCase = enabled }

        /**
         * @param enabled Whether to enable executable checks in the TreeMatch.
         */
        fun setIsExecutable(enabled: Boolean) = apply { this.isExecutable = enabled }

        /**
         * @param enabled Whether to check emptiness in the TreeMatch.
         */
        fun setNonEmpty(enabled: Boolean) = apply { this.nonEmpty = enabled }

        /**
         * @param mimetype The mimetype.
         */
        fun setMimetype(mimetype: String) = apply { this.mimetype = mimetype }

        /**
         * @param treeMatch The TreeMatch to add as child.
         */
        fun addTreeMatch(treeMatch: TreeMatch) = apply { this.children += treeMatch }

        /**
         * Build the TreeMatch element.
         */
        fun build(): TreeMatch = when(this.path) {
            is String -> TreeMatch(
                this.path!!,
                this.treeMatchType,
                this.matchCase,
                this.isExecutable,
                this.nonEmpty,
                this.mimetype,
                this.children
            )
            else -> throw IllegalStateException(buildErrorMessage)
        }
    }

    /**
     * The type of a TreeMatch.
     */
    enum class Type
    {
        FILE,
        DIRECTORY,
        LINK;

        override fun toString(): String = this.name.lowercase()
    }
}
