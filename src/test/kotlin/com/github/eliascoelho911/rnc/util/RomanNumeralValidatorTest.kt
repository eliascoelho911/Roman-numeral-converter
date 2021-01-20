package com.github.eliascoelho911.rnc.util

import com.github.eliascoelho911.rnc.exception.ContainsSpaceException
import com.github.eliascoelho911.rnc.exception.FourOrPlusValuesConsecutiveException
import com.github.eliascoelho911.rnc.exception.UnknownCharException
import com.github.eliascoelho911.rnc.exception.ValueIsBlankException
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

    @Test
    fun `valid if value not contains unknown char`() {
        validate("XXX")
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
    fun `valid if value not contains space`() {
        validate("XXX")
    }

    private fun validate(value: String) {
        RomanNumeralValidator().validate(value)
    }
}