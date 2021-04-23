package com.example.kotlin_coroutine.settings

import com.example.kotlin_coroutine.utils.pref

object Settings {
    var firstTimeLaunch by pref(true)
}