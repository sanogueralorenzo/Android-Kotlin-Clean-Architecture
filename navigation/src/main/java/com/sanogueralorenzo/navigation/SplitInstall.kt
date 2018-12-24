package com.sanogueralorenzo.navigation

import android.content.Context
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

object SplitInstall {

    fun download(c: Context, dynamicModule: String, installedAction: () -> Unit) {
        val manager = SplitInstallManagerFactory.create(c)
        val request = SplitInstallRequest.newBuilder()
            .addModule(dynamicModule)
            .build()
        manager.registerListener {
            if (it.status() == SplitInstallSessionStatus.INSTALLED) installedAction.invoke()
        }
        manager.startInstall(request)
    }
}
