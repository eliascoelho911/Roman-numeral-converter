package com.github.eliascoelho911.rnc.util

import com.github.eliascoelho911.rnc.exception.*

open class RomanNumeralValidator {
    val validChars = listOf("I", "V", "X", "L", "C", "D", "M")

    fun validate(romanNumeral: String) {
        failureIfIsBlank(romanNumeral)
        failureIfContainsSpace(romanNumeral)
        failureIfContainsFourOrPlusConsecutiveChars(romanNumeral)
        failureIfContainsUnknownChar(romanNumeral)
        failureIfAllSubtractionsIsValid(romanNumeral)
    }

    private fun failureIfAllSubtractionsIsValid(romanNumeral: String) {
        romanNumeral.forEachIndexed { index, char ->
            val s = char.toString()

            if (s == "I" || s == "X" || s == "C") {
                val sIndex = validChars.indexOf(s)
                val possibleNeighbors = validChars.subList(0, sIndex + 3)

                val nextChar = romanNumeral.getOrNull(index + 1)
                nextChar?.run {
                    if (!possibleNeighbors.contains(this.toString()))
                        throw SubtractionInvalid(romanNumeral)
                }
            }
        }
    }

    private fun failureIfContainsSpace(romanNumeral: String) {
        if (romanNumeral.contains(" ")) throw ContainsSpaceException()
    }

    private fun failureIfIsBlank(romanNumeral: String) {
        if (romanNumeral.isBlank()) throw ValueIsBlankException()
    }

    private fun failureIfContainsUnknownChar(romanNumeral: String) {
        romanNumeral.forEach {
            if (!validChars.contains(it.toString()))
                throw UnknownCharException(it.toString())
        }
    }

    private fun failureIfContainsFourOrPlusConsecutiveChars(romanNumeral: String) {
        var counter = 1
        var lastChar = ""
        romanNumeral.forEach {
            if (it.toString() == lastChar) {
                counter++
                if (counter >= 4)
                    throw FourOrPlusValuesConsecutiveException(romanNumeral)
            } else counter = 1
            lastChar = it.toString()
        }
    }
}