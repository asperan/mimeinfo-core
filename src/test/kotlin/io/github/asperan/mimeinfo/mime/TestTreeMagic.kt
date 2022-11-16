/*
Copyright (c) 2022, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.mimeinfo.mime

import io.github.asperan.helper.defaultIndentString
import io.github.asperan.helper.defaultPriority
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class TestTreeMagic : FunSpec() {
    init {
        test("Simple TreeMagic XML") {
            val testString = "<treemagic priority=\"$defaultPriority\">\n$defaultIndentString<treematch path=\"/my/custom/path\">\n\n$defaultIndentString</treematch>\n</treemagic>"
            val t = TreeMagic.Builder().addTreeMatch(TreeMatch.Builder().setPath("/my/custom/path").build()).build()
            t.toXmlString(0u).shouldBe(testString)
        }
    }
}
