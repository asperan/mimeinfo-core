/*
Copyright (c) 2022, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.mimeinfo.mime

import io.github.asperan.helper.builder.AbstractBuilder

class MimeInfoSpecs(
    private val mimeTypes: List<MimeTypeSpecs>,
) : ElementWithCompoundChildren() {
    private val xmlNamespace = "http://www.freedesktop.org/standards/shared-mime-info"

    override val elementName: String get() = "mime-info"
    override val attributesString: String get() = "xmlns='${xmlNamespace}'"

    override fun getChildrenString(indentLevel: UInt, indentString: String): String = this.mimeTypes.joinToString("\n") { it.toXmlString(indentLevel, indentString) }

    class Builder : AbstractBuilder<MimeInfoSpecs, Builder>() {
        private var mimeTypes: List<MimeTypeSpecs> = listOf()

        val addMimeType = builderMethod<MimeTypeSpecs> { this.mimeTypes += it }

        override fun build(): MimeInfoSpecs = MimeInfoSpecs(this.mimeTypes)
    }
}