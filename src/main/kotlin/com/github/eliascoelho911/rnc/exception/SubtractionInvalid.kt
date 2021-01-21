package com.github.eliascoelho911.rnc.exception

class SubtractionInvalid(romanNumeral: String) :
    RomanNumeralInvalid("This roman numeral '$romanNumeral' contains one or more subtractions invalid")