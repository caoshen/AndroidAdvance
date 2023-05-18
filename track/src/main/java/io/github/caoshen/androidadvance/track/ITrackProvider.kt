package io.github.caoshen.androidadvance.track

interface ITrackProvider {

    fun isEnabled(): Boolean

    fun getName(): String

    fun onInit()

    fun onEvent(eventName: String, params: TrackParams)
}