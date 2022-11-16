/*
Copyright (c) 2022, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.mimeinfo.mime

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class TestSubClassOf : FunSpec() {
    init {
        test("Simple SubClassOf XML") {
            val testString = "<sub-class-of type=\"text/plain\"/>"
            val s = SubClassOf("text/plain")
            s.toXmlString(0u).shouldBe(testString)
        }
    }
}