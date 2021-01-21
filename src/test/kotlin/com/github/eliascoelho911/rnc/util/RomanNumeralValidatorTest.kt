package com.github.eliascoelho911.rnc.util

import com.github.eliascoelho911.rnc.exception.*
import org.junit.Test
import kotlin.test.assertEquals

class RomanNumeralValidatorTest {
    @Test(expected = FourOrPlusValuesConsecutiveException::class)
    fun `not valid if value contains four or plus consecutive chars`() {
        validate("LXXXX")
    }

    @Test
    fun `validates that validChars is in descending order`() {
        val validator = RomanNumeralValidator()
        val actual = validator.validChars
        val expected = listOf("I", "V", "X", "L", "C", "D", "M")
        assertEquals(expected, actual)
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
    fun `Approve when 'I' subtracting 'V'`() {
        validate("IV")
    }

    @Test
    fun `Approve when 'I' subtracting 'X'`() {
        validate("IX")
    }

    @Test(expected = SubtractionInvalid::class)
    fun `Do not approve when 'I' subtracting bigger then 'X'`() {
        validate("IL")
    }

    @Test
    fun `Approve when 'X' subtracting 'L'`() {
        validate("XL")
    }

    @Test
    fun `Approve when 'X' subtracting 'C'`() {
        validate("XC")
    }

    @Test(expected = SubtractionInvalid::class)
    fun `Do not approve when 'X' subtracting bigger then 'C'`() {
        validate("XM")
    }

    @Test
    fun `Approve when 'C' subtracting 'D'`() {
        validate("CD")
    }

    @Test
    fun `Approve when 'C' subtracting 'M'`() {
        validate("CM")
    }

    private fun validate(value: String) {
        RomanNumeralValidator().validate(value)
    }
}