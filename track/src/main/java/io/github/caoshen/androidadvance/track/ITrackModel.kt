package io.github.caoshen.androidadvance.track

import java.io.Serializable

/**
 * 填充埋点参数
 */
interface ITrackModel : Serializable {
    fun fillTrackParams(trackParams: TrackParams)
}