package com.sanogueralorenzo.sample.presentation

import io.reactivex.Scheduler
import io.reactivex.functions.Function
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Rule to avoid having schedulers all around https://github.com/ReactiveX/RxAndroid/issues/238
 *
 * This rule registers Handlers for RxJava to ensure that subscriptions
 * always subscribeOn and observeOn Schedulers.trampoline().
 * Warning, this rule will reset RxJavaPlugins before and after each test so
 * if the application code uses RxJava plugins this may affect the behaviour of the testing method.
 */
class RxSchedulersOverrideRule : TestRule {

    private val rxJavaImmediateScheduler =
        Function<Scheduler, Scheduler> { Schedulers.trampoline() }

    override fun apply(base: Statement, description: Description): Statement =
        object : Statement() {
            override fun evaluate() {
                RxJavaPlugins.reset()
                RxJavaPlugins.setIoSchedulerHandler(rxJavaImmediateScheduler)
                RxJavaPlugins.setNewThreadSchedulerHandler(rxJavaImmediateScheduler)

                base.evaluate()

                RxJavaPlugins.reset()
            }
        }
}
