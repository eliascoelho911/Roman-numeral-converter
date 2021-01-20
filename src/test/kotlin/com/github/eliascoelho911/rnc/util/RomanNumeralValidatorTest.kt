package com.github.eliascoelho911.rnc.util

import com.github.eliascoelho911.rnc.exception.*
import org.junit.Test

class RomanNumeralValidatorTest {
    @Test(expected = FourOrPlusValuesConsecutiveException::class)
    fun `not valid if value contains four or plus consecutive chars`() {
        validate("LXXXX")
    }

    @Test
    fun `valid if value contains up to three consecutive chars`() {
        validate("LXXX")
    }

    @Test(expected = UnknownCharException::class)
    fun `not valid if value contains unknown char`() {
        validate("XXXS")
    }

    @Test(expected = ValueIsBlankException::class)
    fun `not valid if value is blank`() {
        validate("")
    }

    @Test
    fun `valid if value not is blank`() {
        validate("LL")
    }

    @Test(expected = ContainsSpaceException::class)
    fun `not valid if value contains space`() {
        validate("XX XX")
    }

    @Test
    fun `Approve when 'I' is on left of 'V'`() {
        validate("IV")
    }

    @Test(expected = RomanNumeralInvalid::class)
    fun `Do not approve when 'I' is on right of 'V' with two char`() {
        validate("VI")
    }

    @Test(expected = RomanNumeralInvalid::class)
    fun `Do not approve when 'I' is on right of 'V' with three or more char`() {
        validate("XXI")
    }

    @Test(expected = RomanNumeralInvalid::class)
    fun `Do not approve when 'I' is not close to 'X' or 'V'`() {
        validate("DIC")
    }

    @Test(expected = RomanNumeralInvalid::class)
    fun `Do not approve when 'X' is not close to 'X' or 'V'`() {
        validate("DIC")
    }

    private fun validate(value: String) {
        RomanNumeralValidator().validate(value)
    }
}