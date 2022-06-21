package io.github.caoshen.androidadvance.performance

import android.app.Application

interface ITrack {

    fun startTrack(application: Application)

    fun stopTrack(application: Application)
}
