/*
Copyright (c) 2022, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.mimeinfo.mime

import io.github.asperan.helper.defaultIndentString
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class TestComment : FunSpec() {
    init {
        test("Simple Comment XML") {
            val testString = "<comment >\n${defaultIndentString}test mimetype comment\n</comment>"
            val c = Comment("test mimetype comment")
            c.toXmlString(0u).shouldBe(testString)
        }

        test("Comment XML with xml_lang") {
            val testString = "<comment xml:lang=\"en_US.utf8\">\n${defaultIndentString}test mimetype comment\n</comment>"
            val c = Comment("test mimetype comment", "en_US.utf8")
            c.toXmlString(0u).shouldBe(testString)
        }
    }
}
