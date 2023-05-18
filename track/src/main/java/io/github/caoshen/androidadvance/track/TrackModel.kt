package io.github.caoshen.androidadvance.track

import java.io.Serializable

class TrackModel : ITrackModel, Serializable {

    protected val params by lazy {
        TrackParams()
    }

    operator fun set(key: String, value: Any?) {
        params[key] = value
    }

    fun setIfNull(key: String, value: Any?) {
        params.setIfNull(key, value)
    }

    fun get(key: String, default: String?) = params.get(key, default)

    override fun fillTrackParams(trackParams: TrackParams) {
        trackParams.merge(params)
    }
}