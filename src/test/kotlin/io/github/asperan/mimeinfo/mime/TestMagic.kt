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

class TestMagic : FunSpec() {
    init {
        test("Simple Magic XML with builder") {
            val testString = "<magic priority=\"$defaultPriority\">\n$defaultIndentString<match type=\"${Match.Type.STRING}\" offset=\"0\" value=\"0x45\"/>\n</magic>"
            val magic = Magic.Builder().setPriority(defaultPriority).addMatch(Match(Match.Type.STRING, Match.Offset(0u), "0x45")).build()
            magic.toXmlString(0u).shouldBe(testString)
        }
    }
}
