/*
Copyright (c) 2022, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.mimeinfo.mime

import io.github.asperan.helper.asList
import io.github.asperan.helper.builder.AbstractBuilder
import io.github.asperan.helper.builder.BuilderStateException

/**
 * The `mime-type` element.
 */
class MimeTypeSpecs(
    private val type: Type,
    private val globs: List<Glob>,
    private val globDeleteAll: Boolean,
    private val magics: List<Magic>,
    private val magicDeleteAll: Boolean,
    private val aliases: List<Alias>,
    private val superClasses: List<SubClassOf>,
    private val comments: List<Comment>,
    private val acronyms: List<Acronym>,
    private val expandedAcronyms: List<ExpandedAcronym>,
    private val icon: Icon?,
    private val genericIcon: Icon?,
    private val rootXml: RootXml?,
    private val treeMagics: List<TreeMagic>,
) : ElementWithCompoundChildren() {
    override val elementName: String get() = "mime-type"
    override val attributesString: String get() = "type=\"${this.type}\""

    override fun getChildrenString(indentLevel: UInt, indentString: String): String {
        val children: Sequence<List<MimeTypeElement>> = sequenceOf(
            this.comments,
            this.acronyms,
            this.expandedAcronyms,
            this.superClasses,
            this.aliases,
            this.icon.asList(),
            this.genericIcon.asList(),
            this.rootXml.asList(),
            when (this.globDeleteAll) {
                true -> listOf(GlobDeleteAll)
                false -> listOf()
            },
            this.globs,
            when (this.magicDeleteAll) {
                true -> listOf(MagicDeleteAll)
                false -> listOf()
            },
            this.magics,
            this.treeMagics,
        )
        return children.flatMap {
            it.map { element -> element.toXmlString(indentLevel, indentString) }
        }.joinToString("\n")
    }

    /**
     * Builder for MimeTypes.
     */
    class Builder : AbstractBuilder<MimeTypeSpecs, Builder>() {
        private var type: Type? = null
        private var globs: List<Glob> = listOf()
        private var globDeleteAll: Boolean = false
        private var magics: List<Magic> = listOf()
        private var magicDeleteAll: Boolean = false
        private var aliases: List<Alias> = listOf()
        private var superClasses: List<SubClassOf> = listOf()
        private var comments: List<Comment> = listOf()
        private var acronyms: List<Acronym> = listOf()
        private var expandedAcronyms: List<ExpandedAcronym> = listOf()
        private var icon: Icon? = null
        private var genericIcon: GenericIcon? = null
        private var rootXml: RootXml? = null
        private var treeMagics: List<TreeMagic> = listOf()

        /**
         * @param it The type of the MimeTypeSpecs.
         */
        val setType = builderMethod<Type> { this.type = it }

        /**
         * @param it The glob to add to the MimeTypeSpecs.
         */
        val addGlob = builderMethod<Glob> { this.globs += it }

        /**
         * @param it Whether to add the `glob-deleteall` element.
         */
        val setGlobDeleteAll = builderMethod<Boolean> { this.globDeleteAll = it }

        /**
         * @param it The magic to add to the MimeTypeSpecs.
         */
        val addMagic = builderMethod<Magic> { this.magics += it }

        /**
         * @param it Whether to add the `magic-deleteall` element.
         */
        val setMagicDeleteAll = builderMethod<Boolean> { this.magicDeleteAll = it }

        /**
         * @param it The alias to add.
         */
        val addAlias = builderMethod<Alias> { this.aliases += it }

        /**
         * @param it The SubClassOf to add.
         */
        val addSuperClass = builderMethod<SubClassOf> { this.superClasses += it }

        /**
         * @param it The Comment to add.
         */
        val addComment = builderMethod<Comment> { this.comments += it }

        /**
         * @param it The Acronym to add.
         */
        val addAcronym = builderMethod<Acronym> { this.acronyms += it }

        /**
         * @param it The ExpandedAcronym to Add.
         */
        val addExpandedAcronym = builderMethod<ExpandedAcronym> { this.expandedAcronyms += it }

        /**
         * @param it The Icon to set.
         */
        val setIcon = builderMethod<Icon?> { this.icon = it }

        /**
         * @param it The generic Icon to set.
         */
        val setGenericIcon = builderMethod<GenericIcon?> { this.genericIcon = it }

        /**
         * @param it The RootXml to set.
         */
        val setRootXml = builderMethod<RootXml?> { this.rootXml = it }

        /**
         * @param it The TreeMagic to add.
         */
        val addTreeMagic = builderMethod<TreeMagic> { this.treeMagics += it }

        override fun build(): MimeTypeSpecs = when (this.type) {
            is Type -> MimeTypeSpecs(
                this.type!!,
                this.globs,
                this.globDeleteAll,
                this.magics,
                this.magicDeleteAll,
                this.aliases,
                this.superClasses,
                this.comments,
                this.acronyms,
                this.expandedAcronyms,
                this.icon,
                this.genericIcon,
                this.rootXml,
                this.treeMagics
            )
            else -> throw BuilderStateException()
        }
    }

    /**
     * Type of the MimeType.
     */
    data class Type(
        private val mimeClass: MimeClass,
        private val type: String,
    ) {

        /**
         * Mime classes. They are defined in the MIME type RFC (Section 5 of RFC 2045).
         */
        enum class MimeClass {
            APPLICATION, AUDIO, FONT, IMAGE, MODEL, TEXT, VIDEO;
            override fun toString(): String = this.name.lowercase()
        }
        override fun toString(): String = "${this.mimeClass}/${this.type}"
    }
}
