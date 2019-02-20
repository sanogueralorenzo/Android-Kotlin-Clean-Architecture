package com.sanogueralorenzo.sample.datasource.model

import java.lang.RuntimeException

class TestException(rawObject: Any) : RuntimeException("This is a test exception containing the object $rawObject")
