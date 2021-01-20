package com.github.eliascoelho911.rnc.converter

import com.github.eliascoelho911.rnc.util.RomanNumeralValidator
import com.nhaarman.mockito_kotlin.spy
import org.junit.Test
import org.mockito.Mockito.verify
import kotlin.test.assertEquals

class RomanNumeralToDecimalTest {

    @Test
    fun `must validate value`() {
        val validator = spy(RomanNumeralValidator())
        val romanNumeralToDecimal = RomanNumeralToDecimal(validator)
        val value = "X"
        romanNumeralToDecimal.toConvert(value)
        verify(validator).validate(value)
    }

    @Test
    fun `must convert one symbol correctly`() {
        assertEquals(10.0, toConvert("X"))
    }

    @Test
    fun `must convert several symbols adding`() {
        assertEquals(25.0, toConvert("XXV"))
    }

    @Test
    fun `must convert with one sum and one subtracting`() {
        assertEquals(195.0, toConvert("CXCV"))
    }

    @Test
    fun `must convert with several sums and one subtracting`() {
        assertEquals(196.0, toConvert("CXCVI"))
    }

    @Test
    fun `must convert with several sums and several subtractions`() {
        assertEquals(779.0, toConvert("DCCLXXIX"))
    }

    @Test
    fun `must convert several symbols subtracting`() {
        assertEquals(9.0, toConvert("IX"))
    }

    private fun toConvert(value: String): Double {
        val romanNumeralToDecimal = RomanNumeralToDecimal()
        return romanNumeralToDecimal.toConvert(value)
    }
}