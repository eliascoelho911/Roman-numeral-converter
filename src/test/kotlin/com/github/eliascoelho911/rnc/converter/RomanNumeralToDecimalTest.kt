package com.github.eliascoelho911.rnc.converter

import com.github.eliascoelho911.rnc.exception.FourOrPlusValuesConsecutiveException
import org.junit.Test

class RomanNumeralToDecimalTest {
    @Test(expected = FourOrPlusValuesConsecutiveException::class)
    fun `not must create the conversor when text have four or plus values consecutive`() {
        RomanNumeralToDecimal("XXXX")
    }
}