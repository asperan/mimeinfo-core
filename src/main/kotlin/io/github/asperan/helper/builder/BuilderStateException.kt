/*
Copyright (c) 2022, Alex Speranza

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.asperan.helper.builder

/**
 * This exception can be thrown when a Builder is requested to build an object but the creation cannot be performed.
 */
class BuilderStateException : Exception("The builder requires some values to be set, but they were not set.")
