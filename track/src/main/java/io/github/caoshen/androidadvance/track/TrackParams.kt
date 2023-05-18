package io.github.caoshen.androidadvance.track

import java.io.Serializable
import java.lang.StringBuilder

/**
 * 埋点参数
 */
open class TrackParams : Iterable<Any?>, Serializable {
    private val mTrackMap = HashMap<String, String?>()

    operator fun set(key: String, value: Any?): TrackParams {
        mTrackMap[key] = value?.toString()
        return this
    }

    operator fun get(key: String): String? = mTrackMap[key]

    fun setIfNull(key: String, value: Any?): TrackParams {
        val oldValue = mTrackMap[key]
        if (oldValue == null) {
            mTrackMap[key] = value?.toString()
        }
        return this
    }

    fun get(key: String, def: String?): String? = mTrackMap[key] ?: def

    fun merge(other: TrackParams?): TrackParams {
        other?.let {
            for ((key, value) in it) {
                setIfNull(key, value)
            }
        }
        return this
    }

    override fun iterator() = mTrackMap.iterator()

    override fun toString(): String {
        return StringBuilder().apply {
            append("[")
            for ((key, value) in mTrackMap) {
                append(" $key = $value ,")
            }
            deleteCharAt(length - 1)
            append("]")
        }.toString()
    }
}