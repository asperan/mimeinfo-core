/*
Copyright (c) 2022, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.mimeinfo.mime

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class TestAcronym : FunSpec() {
    init {
        test("Simple Acronym XML") {
            val testString = "<acronym >DRM</acronym>"
            val a = Acronym("DRM")
            a.toXmlString(0u).shouldBe(testString)
        }

        test("Acronym XML with xml:lang") {
            val testString = "<acronym xml:lang=\"en_US.utf8\">DRM</acronym>"
            val a = Acronym("DRM", "en_US.utf8")
            a.toXmlString(0u).shouldBe(testString)
        }
    }
}
