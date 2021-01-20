package com.github.eliascoelho911.rnc.util

import com.github.eliascoelho911.rnc.exception.*

open class RomanNumeralValidator {
    private val validChars = listOf("I", "V", "X", "L", "C", "D", "M")

    fun validate(romanNumeral: String) {
        failureIfIsBlank(romanNumeral)
        failureIfContainsSpace(romanNumeral)
        failureIfContainsFourOrPlusConsecutiveChars(romanNumeral)
        failureIfContainsUnknownChar(romanNumeral)
        //IV
        failureWhenNeighborIsNot(romanNumeral, "I", rightNeighbors = listOf("V", "X", "I"))
        failureWhenNeighborIsNot(romanNumeral, "X", rightNeighbors = listOf("L", "C", "X"))
        failureWhenNeighborIsNot(romanNumeral, "C", rightNeighbors = listOf("D", "M", "C"))
    }

    private fun failureWhenNeighborIsNot(
        romanNumeral: String,
        reference: String,
        leftNeighbors: List<String>? = null,
        rightNeighbors: List<String>? = null
    ) {
        romanNumeral.forEachIndexed { index, char ->
            if (char.toString() == reference) {
                val neighborsDontAreValid =
                    !(validateLeftNeighbor(leftNeighbors, romanNumeral, index) &&
                            validateRightNeighbor(rightNeighbors, romanNumeral, index))

                if (neighborsDontAreValid)
                    throw RomanNumeralInvalid()
            }
        }
    }

    private fun validateRightNeighbor(
        rightNeighbors: List<String>?,
        romanNumeral: String,
        index: Int
    ) = rightNeighbors?.run { haveCorrectNeighbors(romanNumeral, this, index, 1) } ?: true

    private fun validateLeftNeighbor(
        leftNeighbors: List<String>?,
        romanNumeral: String,
        index: Int
    ) = leftNeighbors?.run { haveCorrectNeighbors(romanNumeral, this, index, -1) } ?: true

    private fun haveCorrectNeighbors(
        romanNumeral: String,
        neighbors: List<String>,
        referenceIndex: Int,
        neighborsOffsetIndex: Int
    ): Boolean {
        val neighbor = findByIndex(romanNumeral, referenceIndex + neighborsOffsetIndex)
        return neighbor?.run { neighbors.contains(neighbor) } ?: true
    }

    private fun findByIndex(romanNumeral: String, index: Int): String? {
        return romanNumeral.getOrNull(index)?.toString()
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