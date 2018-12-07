package com.sanogueralorenzo.navigation

import android.content.Intent

interface DynamicFeature {
    val dynamicModule: String
    val dynamicIntent: Intent?
}
