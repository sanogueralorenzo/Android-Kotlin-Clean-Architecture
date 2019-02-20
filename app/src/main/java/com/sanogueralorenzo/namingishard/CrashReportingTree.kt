package com.sanogueralorenzo.namingishard

import com.crashlytics.android.Crashlytics
import timber.log.Timber

class CrashReportingTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        Crashlytics.log(message)
        t?.let { Crashlytics.logException(it) }
    }

    override fun e(t: Throwable?) {
        Crashlytics.logException(t)
        super.e(t)
    }
}
