package com.github.eliascoelho911.rnc.util

import com.github.eliascoelho911.rnc.exception.ContainsSpaceException
import com.github.eliascoelho911.rnc.exception.FourOrPlusValuesConsecutiveException
import com.github.eliascoelho911.rnc.exception.UnknownCharException
import com.github.eliascoelho911.rnc.exception.ValueIsBlankException

open class RomanNumeralValidator {
    private val validChars = listOf("I", "V", "X", "L", "C", "D", "M")

    fun validate(value: String) {
        failureIfValueIsBlank(value)
        failureIfValueContainsSpace(value)
        failureIfContainsFourOrPlusConsecutiveChars(value)
        failureIfContainsUnknownChar(value)
    }

    private fun failureIfValueContainsSpace(value: String) {
        if (value.contains(" ")) throw ContainsSpaceException()
    }

    private fun failureIfValueIsBlank(value: String) {
        if (value.isBlank()) throw ValueIsBlankException()
    }

    private fun failureIfContainsUnknownChar(value: String) {
        value.forEach {
            if (!validChars.contains(it.toString()))
                throw UnknownCharException(it.toString())
        }
    }

    private fun failureIfContainsFourOrPlusConsecutiveChars(value: String) {
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