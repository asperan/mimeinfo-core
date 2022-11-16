/*
Copyright (c) 2022, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.mimeinfo.mime

import io.github.asperan.helper.computeIndentPrefix
import io.github.asperan.helper.defaultIndentString
import io.github.asperan.helper.formatOrEmptyString

/**
 * Base for all elements in a mimetype file.
 */
interface MimeTypeElement {
    val elementName: String
    val attributesString: String
    fun toXmlString(indentLevel: UInt, indentString: String = defaultIndentString): String
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
    override val elementName: String get() = "${this.baseElementName}-deleteall"
    final override val attributesString: String get() = ""
}

/**
 * Convenience class for elements with general children, i.e. in the form `<abc> ... children ... </abc>`.
 */
abstract class ElementWithChildren : MimeTypeElement
{
    abstract fun getChildrenString(indentLevel: UInt, indentString: String): String

    final override fun toXmlString(indentLevel: UInt, indentString: String): String =
        computeIndentPrefix(indentLevel, indentString) + "<${this.elementName} ${this.attributesString}>\n" +
        this.getChildrenString(indentLevel + 1u, indentString) +
        "\n" + computeIndentPrefix(indentLevel, indentString) + "</${this.elementName}>"
}

/**
 * Convenience class for elements with a textual child, i.e. in the form `<abc>Text</abc>`.
 */
abstract class ElementWithTextualChild(
    private val value: String,
    private val xmlLang: String? = null
) : ElementWithChildren()
{
    final override val attributesString: String get() = this.xmlLang.formatOrEmptyString { "xml:lang=\"${this.xmlLang}\"" }

    final override fun getChildrenString(indentLevel: UInt, indentString: String): String = computeIndentPrefix(indentLevel, indentString) + this.value
}

/**
 * Convenience class for elements with compound children, i.e. in the form `<abc><def> ... children ... </def></abc>`.
 */
abstract class ElementWithCompoundChildren : ElementWithChildren()
