package com.github.eliascoelho911.rnc.exception

import java.lang.RuntimeException

class FourOrPlusValuesConsecutiveException(value: String) : RuntimeException("The value $value have four or plus characters consecutive")