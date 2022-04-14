package io.github.caoshen.androidadvance.githubtrending.data.entity

import java.lang.Exception

sealed class ResultX<out R : Any> {

    data class Success<out T : Any>(val data: T) : ResultX<T>()

    data class Error(val exception: Exception) : ResultX<Nothing>()

    object Loading: ResultX<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success -> return "Success[data=$data]"
            is Error -> return "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}
