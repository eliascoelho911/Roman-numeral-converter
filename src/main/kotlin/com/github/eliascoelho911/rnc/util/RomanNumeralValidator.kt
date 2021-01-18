package com.github.eliascoelho911.rnc.util

import com.github.eliascoelho911.rnc.exception.ContainsSpaceException
import com.github.eliascoelho911.rnc.exception.ContainsUnknownCharException
import com.github.eliascoelho911.rnc.exception.FourOrPlusValuesConsecutiveException
import com.github.eliascoelho911.rnc.exception.ValueIsBlankException

class RomanNumeralValidator(private val value: String) {
    private val validChars = listOf("I", "V", "X", "L", "C", "D", "M")

    fun validate() {
        failureIfValueIsBlank()
        failureIfValueContainsSpace()
        failureIfContainsFourOrPlusConsecutiveChars()
        failureIfContainsUnknownChar()
    }

    private fun failureIfValueContainsSpace() {
        if (value.contains(" ")) throw ContainsSpaceException()
    }

    private fun failureIfValueIsBlank() {
        if (value.isBlank()) throw ValueIsBlankException()
    }

    private fun failureIfContainsUnknownChar() {
        value.forEach {
            if (!validChars.contains(it.toString()))
                throw ContainsUnknownCharException(it.toString())
        }
    }

    private fun failureIfContainsFourOrPlusConsecutiveChars() {
        var counter = 1
        var lastChar = ""
        value.forEach {
            if (it.toString() == lastChar) {
                counter++
                if (counter >= 4)
                    throw FourOrPlusValuesConsecutiveException(value)
            } else counter = 1
            lastChar = it.toString()
        }
    }
}