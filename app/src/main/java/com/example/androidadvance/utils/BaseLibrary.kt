package com.example.androidadvance.utils

import android.content.Context

object BaseLibrary {

    private var sContext: Context? = null

    fun init(context: Context) {
        sContext = context
    }

    fun getContext(): Context? {
        return sContext
    }
}