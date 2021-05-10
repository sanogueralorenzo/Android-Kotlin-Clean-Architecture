package com.sanogueralorenzo.namingishard

import android.app.Activity
import android.app.Application
import android.content.pm.ActivityInfo
import android.os.Bundle
import com.sanogueralorenzo.navigation.core.ContainerActivity
import timber.log.Timber

class ActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        activity.log("onCreate()")
        activity.registerFragmentLifecycleCallbacks()
        activity.allowDebugRotation()
    }

    override fun onActivityResumed(activity: Activity) =
        activity.log("onResume()")

    override fun onActivityPaused(activity: Activity) =
        activity.log("onPause()")

    override fun onActivityDestroyed(activity: Activity) =
        activity.log("onDestroy()")

    override fun onActivityStarted(activity: Activity) {
        // no-op
    }

    override fun onActivityStopped(activity: Activity) {
        // no-op
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        // no-op
    }
}

private fun Activity.log(lifecycle: String) = javaClass.canonicalName!!.let {
    if (it.contains(ContainerActivity::class.java.name).not()) Timber.tag(it).i(lifecycle)
}

private fun Activity.allowDebugRotation() {
    requestedOrientation =
        if (BuildConfig.DEBUG) ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        else ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
}
