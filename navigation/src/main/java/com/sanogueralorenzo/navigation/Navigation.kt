package com.sanogueralorenzo.navigation

import android.content.Intent

private const val PACKAGE_NAME = "com.sanogueralorenzo.namingishard"

private val classMap = mutableMapOf<String, Class<*>>()
private inline fun <reified T : Any> Any.castOrNull() = this as? T

internal fun <T> loadClassOrNull(className: String): Class<out T>? {
    return classMap.getOrPut(className) {
        try {
            Class.forName(className)
        } catch (e: ClassNotFoundException) {
            return null
        }
    }.castOrNull()
}

private val intentMap = mutableMapOf<String, Intent>()
private fun intentTo(className: String): Intent = Intent(Intent.ACTION_VIEW).setClassName(PACKAGE_NAME, className)

internal fun loadIntentOrNull(className: String): Intent? = intentMap.getOrPut(className) {
    try {
        Class.forName(className).run { intentTo(className) }
    } catch (e: ClassNotFoundException) {
        return null
    }
}
