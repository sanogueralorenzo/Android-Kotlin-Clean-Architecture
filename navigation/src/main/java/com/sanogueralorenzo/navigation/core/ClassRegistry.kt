package com.sanogueralorenzo.navigation.core

import android.app.Activity
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

/**
 * Helps to load a class by fully qualified name, with a cache.
 */
object ClassRegistry {
    private val CLASS_MAP = ConcurrentHashMap<String, Class<*>>()

    @JvmStatic
    fun <T : Activity> loadActivityOrThrow(className: String): Class<T> =
        loadClassOrThrow(className, Activity::class)

    @JvmStatic
    fun <T : Fragment> loadFragmentOrThrow(className: String): Class<T> =
        loadClassOrThrow(className, Fragment::class)

    @JvmStatic
    fun <T : Service> loadServiceOrThrow(className: String): Class<T> =
        loadClassOrThrow(className)

    @JvmStatic
    fun <T : BroadcastReceiver> loadReceiverOrThrow(className: String): Class<T> =
        loadClassOrThrow(className)

    @Suppress("UNCHECKED_CAST")
    fun <T> loadClassOrNull(className: String): Class<T>? {
        return CLASS_MAP.getOrPut(className) {
            try {
                Class.forName(className)
            } catch (e: ClassNotFoundException) {
                // Can't store a null value in the concurrent map
                return null
            }
        } as? Class<T>
    }

    @Throws(ClassNotFoundException::class)
    private fun <T> loadClassOrThrow(className: String, type: KClass<*>? = null): Class<T> =
        loadClassOrNull(className) ?: throw ClassNotFoundException("Class not found $className")

    /** Given a FQN class name, returns the simple name. */
    fun simpleName(className: String) = className.split(".").lastOrNull()
}

// object ClassRegistry {
//    private val CLASS_MAP = ConcurrentHashMap<String, Class<*>>()
//
//    fun loadFragment(className: String): Class<Fragment> = loadClass(className)
//
//    @Suppress("UNCHECKED_CAST")
//    private fun <T> loadClass(className: String): Class<T> =
//        CLASS_MAP.getOrPut(className) { Class.forName(className) } as Class<T>
// }

fun loadFragment(className: String, args: Bundle? = null): Fragment =
    ClassRegistry.loadFragmentOrThrow<Fragment>(className).newInstance().apply { arguments = args }

fun loadIntent(className: String): Intent =
    Intent(Intent.ACTION_VIEW).setClassName("com.sanogueralorenzo.namingishard", className)
