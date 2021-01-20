package com.github.eliascoelho911.rnc.exception

class FourOrPlusValuesConsecutiveException(value: String) :
    RomanNumeralInvalid("The value $value have four or plus characters consecutive")