package com.github.eliascoelho911.rnc.exception

import java.lang.RuntimeException

class ContainsUnknownCharException(unknownChar: String) : RuntimeException("'$unknownChar' is unknown")
