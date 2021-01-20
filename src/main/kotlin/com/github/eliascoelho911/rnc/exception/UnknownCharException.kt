package com.github.eliascoelho911.rnc.exception

class UnknownCharException(unknownChar: String) : RomanNumeralInvalid("'$unknownChar' is unknown")
