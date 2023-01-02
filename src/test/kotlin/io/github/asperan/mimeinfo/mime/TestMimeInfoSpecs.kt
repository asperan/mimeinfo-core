/*
Copyright (c) 2023, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.mimeinfo.mime

import io.github.asperan.helper.DEFAULT_INDENT_STRING
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class TestMimeInfoSpecs : FunSpec() {
    init {
        test("Simple MimeInfoSpecs XML") {
            val testString = "<mime-info xmlns='http://www.freedesktop.org/standards/shared-mime-info'>\n" +
                "$DEFAULT_INDENT_STRING<mime-type type=\"text/drmime\">\n" +
                "${DEFAULT_INDENT_STRING.repeat(2)}<glob pattern=\"*.drmime\"/>\n" +
                "$DEFAULT_INDENT_STRING</mime-type>\n" +
                "</mime-info>"
            val m = MimeInfoSpecs.Builder().addMimeType(
                MimeTypeSpecs.Builder()
                    .setType(MimeTypeSpecs.Type(MimeTypeSpecs.Type.MimeClass.TEXT, "drmime"))
                    .addGlob(
                        Glob("*.drmime")
                    ).build()
            ).build()
            m.toXmlString(0u).shouldBe(testString)
        }
    }
}
