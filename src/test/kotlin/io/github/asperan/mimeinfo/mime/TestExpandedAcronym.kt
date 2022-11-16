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

class TestExpandedAcronym : FunSpec() {
    init {
        test("Simple ExpandedAcronym XML") {
            val testString = "<expanded-acronym >\n${DEFAULT_INDENT_STRING}Test Expanded Acronym\n</expanded-acronym>"
            val e = ExpandedAcronym("Test Expanded Acronym")
            e.toXmlString(0u).shouldBe(testString)
        }

        test("ExpandedAcronym XML with xml:lang") {
            val testString = "<expanded-acronym xml:lang=\"en_US.utf8\">\n" +
                "${DEFAULT_INDENT_STRING}Test Expanded Acronym\n" +
                "</expanded-acronym>"
            val e = ExpandedAcronym("Test Expanded Acronym", "en_US.utf8")
            e.toXmlString(0u).shouldBe(testString)
        }
    }
}
