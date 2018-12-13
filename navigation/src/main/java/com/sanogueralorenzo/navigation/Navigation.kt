package com.sanogueralorenzo.navigation

import android.content.Context
import android.content.Intent
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

private const val PACKAGE_NAME = "com.sanogueralorenzo.namingishard"

private fun intentTo(className: String): Intent =
    Intent(Intent.ACTION_VIEW).setClassName(PACKAGE_NAME, className)

internal fun loadIntentOrNull(className: String): Intent? =
    try {
        Class.forName(className).run { intentTo(className) }
    } catch (e: ClassNotFoundException) {
        null
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
