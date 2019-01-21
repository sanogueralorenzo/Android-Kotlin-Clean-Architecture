package com.sanogueralorenzo.sample.presentation

import android.widget.ImageView
import com.sanogueralorenzo.presentation.loadImageRound

fun ImageView.loadAvatar(email: String) =
    loadImageRound("https://api.adorable.io/avatars/285/$email")
