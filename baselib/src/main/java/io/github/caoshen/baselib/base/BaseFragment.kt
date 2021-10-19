package io.github.caoshen.baselib.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import io.github.caoshen.baselib.anno.FragmentConfiguration

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    private var useEventBus = false

    init {
        this.javaClass.getAnnotation(FragmentConfiguration::class.java)?.let {
            useEventBus = it.useEventBus
        }
    }
}