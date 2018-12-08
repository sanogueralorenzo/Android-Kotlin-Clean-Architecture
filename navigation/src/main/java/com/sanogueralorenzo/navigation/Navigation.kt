package com.sanogueralorenzo.navigation

import android.content.Context
import android.content.Intent
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

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

fun dynamicModuleDownload(c: Context, dynamicModule: String, installedAction: () -> Unit) {
    val manager = SplitInstallManagerFactory.create(c)
    val request = SplitInstallRequest.newBuilder()
        .addModule(dynamicModule)
        .build()
    manager.registerListener {
        if (it.status() == SplitInstallSessionStatus.INSTALLED) installedAction.invoke()
    }
    manager.startInstall(request)
}
