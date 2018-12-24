package com.sanogueralorenzo.navigation

private val classMap = mutableMapOf<String, Class<*>>()

@Suppress("UNCHECKED_CAST")
internal fun <T> String.loadClassOrNull(): T? =
    classMap.getOrPut(this) {
        try {
            Class.forName(this)
        } catch (e: ClassNotFoundException) {
            return null
        }
    }.run { this as? T }
