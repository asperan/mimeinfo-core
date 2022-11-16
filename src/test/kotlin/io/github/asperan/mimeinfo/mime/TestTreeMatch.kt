/*
Copyright (c) 2022, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.mimeinfo.mime

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class TestTreeMatch : FunSpec() {
    init {
        test("Simple TreeMatch XML") {
            val testString = "<treematch path=\"/my/custom/path\">\n\n</treematch>"
            val t = TreeMatch.Builder().setPath("/my/custom/path").build()
            t.toXmlString(0u).shouldBe(testString)
        }
    }
}
