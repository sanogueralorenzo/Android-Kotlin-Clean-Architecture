package com.sanogueralorenzo.navigation

import android.content.Intent

private const val PACKAGE_NAME = "com.sanogueralorenzo.namingishard"

private fun intentTo(className: String): Intent =
    Intent(Intent.ACTION_VIEW).setClassName(PACKAGE_NAME, className)

internal fun String.loadIntentOrNull(): Intent? =
    try {
        Class.forName(this).run { intentTo(this@loadIntentOrNull) }
    } catch (e: ClassNotFoundException) {
        null
    }
