/*
Copyright (c) 2022, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.mimeinfo.mime

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class TestAlias : FunSpec() {
    init {
        test("Simple Alias XML") {
            val testString = "<alias type=\"text/x-drm\"/>"
            val a = Alias("text/x-drm")
            a.toXmlString(0u).shouldBe(testString)
        }
    }
}