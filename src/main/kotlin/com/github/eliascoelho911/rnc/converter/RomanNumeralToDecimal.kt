package com.github.eliascoelho911.rnc.converter

import com.github.eliascoelho911.rnc.util.RomanNumeralValidator

class RomanNumeralToDecimal(value: String) {
    private val validator = RomanNumeralValidator(value)

    init {
        validate()
    }

    private fun validate() {
        validator.validate()
    }
}