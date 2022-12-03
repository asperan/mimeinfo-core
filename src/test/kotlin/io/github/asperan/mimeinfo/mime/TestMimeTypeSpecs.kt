/*
Copyright (c) 2022, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.mimeinfo.mime

import io.github.asperan.helper.DEFAULT_INDENT_STRING
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class TestMimeTypeSpecs : FunSpec() {
    init {
        test("Simple MimeTypeSpecs XML") {
            val testString = "<mime-type type=\"text/drawio\">\n" +
                "$DEFAULT_INDENT_STRING<comment >Just a comment</comment>\n" +
                "$DEFAULT_INDENT_STRING<acronym >DRAWIO</acronym>\n" +
                "$DEFAULT_INDENT_STRING<expanded-acronym >Drawio Project</expanded-acronym>\n" +
                "$DEFAULT_INDENT_STRING<sub-class-of type=\"text/plain\"/>\n" +
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
