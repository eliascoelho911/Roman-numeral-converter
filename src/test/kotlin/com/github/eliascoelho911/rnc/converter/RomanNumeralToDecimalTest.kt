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
        assertEquals(10, toConvert("X"))
    }

    @Test
    fun `must convert several symbols adding`() {
        assertEquals(25, toConvert("XXV"))
    }

    @Test
    fun `must convert with one sum and one subtracting`() {
        assertEquals(195, toConvert("CXCV"))
    }

    @Test
    fun `must convert with several sums and one subtracting`() {
        assertEquals(196, toConvert("CXCVI"))
    }

    @Test
    fun `must convert with several sums and several subtractions`() {
        assertEquals(779, toConvert("DCCLXXIX"))
    }

    @Test
    fun `must convert several symbols subtracting`() {
        assertEquals(9, toConvert("IX"))
    }

    private fun toConvert(value: String): Int {
        val romanNumeralToDecimal = RomanNumeralToDecimal()
        return romanNumeralToDecimal.toConvert(value)
    }
}