package io.github.caoshen.androidadvance.track

/**
 * 关联埋点
 */
interface ITrackNode : ITrackModel {
    /**
     * 父节点，建立一个页面内的责任链
     */
    fun parentTrackNode(): ITrackNode?

    /**
     * 根源节点，建立跳转逻辑
     */
    fun referredTrackNode(): ITrackNode?
}