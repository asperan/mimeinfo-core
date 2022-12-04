/*
Copyright (c) 2022, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.mimeinfo.mime

import io.github.asperan.helper.asList

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
    private val genericIcon: GenericIcon?,
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
    class Builder {
        private val buildErrorMessage = "Building a MimeTypeSpecs require a non-null Type, but the given type was null."
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
         * @param type The type of the MimeTypeSpecs.
         */
        fun setType(type: Type) = apply { this.type = type }

        /**
         * @param glob The glob to add to the MimeTypeSpecs.
         */
        fun addGlob(glob: Glob) = apply { this.globs += glob }

        /**
         * @param enabled Whether to add the `glob-deleteall` element.
         */
        fun setGlobDeleteAll(enabled: Boolean) = apply { this.globDeleteAll = enabled }

        /**
         * @param magic The magic to add to the MimeTypeSpecs.
         */
        fun addMagic(magic: Magic) = apply { this.magics += magic }

        /**
         * @param enabled Whether to add the `magic-deleteall` element.
         */
        fun setMagicDeleteAll(enabled: Boolean) = apply { this.magicDeleteAll = enabled }

        /**
         * @param alias The alias to add.
         */
        fun addAlias(alias: Alias) = apply { this.aliases += alias }

        /**
         * @param superClass The SubClassOf to add.
         */
        fun addSuperClass(superClass: SubClassOf) = apply { this.superClasses += superClass }

        /**
         * @param comment The Comment to add.
         */
        fun addComment(comment: Comment) = apply { this.comments += comment }

        /**
         * @param acronym The Acronym to add.
         */
        fun addAcronym(acronym: Acronym) = apply { this.acronyms += acronym }

        /**
         * @param expandedAcronym The ExpandedAcronym to Add.
         */
        fun addExpandedAcronym(expandedAcronym: ExpandedAcronym) = apply { this.expandedAcronyms += expandedAcronym }

        /**
         * @param icon The Icon to set.
         */
        fun setIcon(icon: Icon) = apply { this.icon = icon }

        /**
         * @param genericIcon The generic Icon to set.
         */
        fun setGenericIcon(genericIcon: GenericIcon) = apply { this.genericIcon = genericIcon }

        /**
         * @param rootXml The RootXml to set.
         */
        fun setRootXml(rootXml: RootXml) = apply { this.rootXml = rootXml }

        /**
         * @param treeMagic The TreeMagic to add.
         */
        fun addTreeMagic(treeMagic: TreeMagic) = apply { this.treeMagics += treeMagic }

        /**
         * Build the MimeType element.
         */
        fun build(): MimeTypeSpecs = when (this.type) {
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
            else -> throw IllegalStateException(buildErrorMessage)
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
