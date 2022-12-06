package io.github.caoshen.baselib

import io.github.caoshen.baselib.log.Logger

inline fun Logger.debug(e: Exception? = null, lazyMessage: () -> String) {
    if (isDebugEnabled) {
        debug(lazyMessage(), e)
    }
}