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

class TestMimeTypeSpecs : FunSpec() {
    init {
        test("Simple MimeTypeSpecs XML") {
            val testString = "<mime-type type=\"text/drawio\">\n" +
                "$defaultIndentString<comment >\n" +
                        "${defaultIndentString.repeat(2)}Just a comment\n" +
                    "$defaultIndentString</comment>\n" +
                    "$defaultIndentString<acronym >\n" +
                        "${defaultIndentString.repeat(2)}DRAWIO\n" +
                    "$defaultIndentString</acronym>\n" +
                    "$defaultIndentString<expanded-acronym >\n" +
                        "${defaultIndentString.repeat(2)}Drawio Project\n" +
                    "$defaultIndentString</expanded-acronym>\n" +
                    "$defaultIndentString<sub-class-of type=\"text/plain\"/>\n" +
                "</mime-type>"
            val m = MimeTypeSpecs.Builder()
                .setType(MimeTypeSpecs.Type(MimeTypeSpecs.Type.MimeClass.TEXT, "drawio"))
                .addSuperClass(SubClassOf("text/plain"))
                .addComment(Comment("Just a comment"))
                .addAcronym(Acronym("DRAWIO"))
                .addExpandedAcronym(ExpandedAcronym("Drawio Project"))
                .build()
            m.toXmlString(0u).shouldBe(testString)
        }
    }
}
