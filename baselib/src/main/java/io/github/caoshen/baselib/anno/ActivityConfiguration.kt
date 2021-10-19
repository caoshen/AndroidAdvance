package io.github.caoshen.baselib.anno

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.ANNOTATION_CLASS)
annotation class ActivityConfiguration(
    val useEventBus: Boolean = false,
    val needLogin: Boolean = false
)
