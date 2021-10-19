package io.github.caoshen.baselib.anno

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
annotation class FragmentConfiguration(
    val useEventBus: Boolean = false
)
