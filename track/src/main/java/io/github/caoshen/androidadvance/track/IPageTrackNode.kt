package io.github.caoshen.androidadvance.track

interface IPageTrackNode : ITrackModel {
    fun referredKeyMap(): Map<String, String>?

    fun referredSnapshot(): ITrackNode?
}