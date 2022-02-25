package io.github.caoshen.androidadvance.kotlin.coroutine

import kotlinx.coroutines.Job

interface Deferred<in T> : Job {
    public suspend fun await(value: T)
}