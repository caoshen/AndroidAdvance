package io.github.caoshen.androidadvance.track

class Tracker {
    companion object {
        internal var providers: MutableList<ITrackProvider> = ArrayList()

        var debug: Boolean = false

        var referredKeyMap: Map<String, String>? = null

        fun registerProvider(provider: ITrackProvider) {
            providers.add(provider.apply {
                onInit()
            })
        }

        fun dispatchEvent(event: String, params: TrackParams) {
            
        }
    }
}

