/*
Copyright (c) 2023, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.mimeinfo.mime

import io.github.asperan.helper.DEFAULT_INDENT_STRING
import io.github.asperan.helper.computeIndentPrefix
import io.github.asperan.helper.formatOrEmptyString

/**
 * Base for all elements in a mimetype file.
 */
interface MimeTypeElement {
    /**
     * The name of the element. It is given in the shared MIME system documentation.
     */
    val elementName: String

    /**
     * The string defining the attributes. Every element type has its own attributes.
     * View the documentation for a list of them.
     */
    val attributesString: String

    /**
     * Convert the element in a xml string.
     *
     * @param indentLevel The level of indentation.
     * @param indentString The prefix to use for indentation.
     *
     * @return The XML corresponding to the element.
     */
    fun toXmlString(indentLevel: UInt, indentString: String = DEFAULT_INDENT_STRING): String
}

/**
 * Convenience class for closed elements i.e. those without childs, in the form of `<foo />`.
 */
abstract class ClosedElement : MimeTypeElement {
    final override fun toXmlString(indentLevel: UInt, indentString: String): String =
        computeIndentPrefix(indentLevel, indentString) + "<" + this.elementName + " " + this.attributesString + "/>"
}

/**
 * Convenience class for "deleteall" elements.
 *
 * This class should not be needed outside the library.
 */
internal abstract class DeleteAllElement(
    private val baseElementName: String,
) : ClosedElement() {
    final override val elementName: String get() = "${this.baseElementName}-deleteall"
    final override val attributesString: String get() = ""
}

/**
 * Convenience class for elements with general children, i.e. in the form `<abc> ... children ... </abc>`.
 */
abstract class ElementWithChildren : MimeTypeElement {
    /**
     * @param indentLevel The children indent level. It should be increased by 1 for each children level.
     * @param indentString The children indent string.
     *
     * @return The children of the element as a single string.
     */
    protected abstract fun getChildrenString(indentLevel: UInt, indentString: String): String
}

/**
 * Convenience class for elements with a textual child, i.e. in the form `<abc>Text</abc>`.
 */
abstract class ElementWithTextualChild(
    private val value: String,
    private val xmlLang: String? = null
) : ElementWithChildren() {
    final override val attributesString: String get() =
        this.xmlLang.formatOrEmptyString { "xml:lang=\"${this.xmlLang}\"" }

    final override fun getChildrenString(indentLevel: UInt, indentString: String): String = this.value

    final override fun toXmlString(indentLevel: UInt, indentString: String): String =
        computeIndentPrefix(indentLevel, indentString).let {
            "$it<${this.elementName} ${this.attributesString}>" +
                this.getChildrenString(indentLevel + 1u, indentString) +
                "</${this.elementName}>"
        }
}

/**
 * Convenience class for elements with compound children, i.e. in the form `<abc><def> ... children ... </def></abc>`.
 */
abstract class ElementWithCompoundChildren : ElementWithChildren() {
    final override fun toXmlString(indentLevel: UInt, indentString: String): String =
        computeIndentPrefix(indentLevel, indentString).let {
            "$it<${this.elementName} ${this.attributesString}>\n" +
                "${this.getChildrenString(indentLevel + 1u, indentString)}\n" +
                "$it</${this.elementName}>"
        }
}
