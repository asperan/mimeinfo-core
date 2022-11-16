/*
Copyright (c) 2022, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.helper

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class TestFunctions : FunSpec() {
    init {
        test("Compute prefix with 0 length should be an empty string") {
            computeIndentPrefix(0u, defaultIndentString).shouldBe("")
        }

        test("computePrefix should repeat the indentString for the given number of times") {
            computeIndentPrefix(3u, defaultIndentString).shouldBe(defaultIndentString + defaultIndentString + defaultIndentString)
        }
    }
}
