/*
Copyright (c) 2022, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.mimeinfo.mime

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class TestMatch : FunSpec() {
    init {
        test("MatchType value should be printed in lowercase") {
            val type: Match.Type = Match.Type.STRING
            type.toString().shouldBe("string")
        }

        test("Simple Match XML") {
            val testString = "<match type=\"${Match.Type.STRING}\" offset=\"0\" value=\"0x45\"/>"
            val m = Match(Match.Type.STRING, Match.Offset(0u), "0x45")
            m.toXmlString(0u).shouldBe(testString)
        }
    }
}
