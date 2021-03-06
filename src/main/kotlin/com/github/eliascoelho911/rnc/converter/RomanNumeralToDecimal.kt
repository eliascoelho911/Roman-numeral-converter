package com.github.eliascoelho911.rnc.converter

import com.github.eliascoelho911.rnc.exception.UnknownCharException
import com.github.eliascoelho911.rnc.util.RomanNumeralValidator

class RomanNumeralToDecimal(private val validator: RomanNumeralValidator = RomanNumeralValidator()) {

    private val conversionTable =
        mapOf("I" to 1, "V" to 5, "X" to 10, "L" to 50, "C" to 100, "D" to 500, "M" to 1000)

    private fun validate(value: String) {
        validator.validate(value)
    }

    fun toConvert(value: String): Int {
        validate(value)
        var total = 0
        value.forEachIndexed { index, char ->
            val currentDecimalNumber = findDecimalNumber(char.toString())
            val nextDecimalNumber = findNextDecimalNumber(value, index)
            total = subtractOrSumWithTotal(nextDecimalNumber, currentDecimalNumber, total)
        }
        return total
    }

    private fun findNextDecimalNumber(value: String, index: Int): Int {
        val indexNext = index + 1
        return if (value.length > indexNext) findDecimalNumber(value[indexNext].toString()) else 0
    }

    private fun subtractOrSumWithTotal(nextDecimalNumber: Int, currentDecimalNumber: Int, total: Int): Int {
        return when {
            currentDecimalNumber < nextDecimalNumber -> total - currentDecimalNumber
            else -> total + currentDecimalNumber
        }
    }

    private fun findDecimalNumber(s: String) = conversionTable[s] ?: throw UnknownCharException(s)
}