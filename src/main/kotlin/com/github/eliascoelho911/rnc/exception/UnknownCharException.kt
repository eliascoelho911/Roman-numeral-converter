package com.github.eliascoelho911.rnc.exception

class UnknownCharException(unknownChar: String) : RuntimeException("'$unknownChar' is unknown")
