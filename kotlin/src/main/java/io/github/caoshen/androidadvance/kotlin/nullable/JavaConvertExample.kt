package io.github.caoshen.androidadvance.kotlin.nullable

class JavaConvertExample {
    private var name: String? = null

    fun test() {
        val count = name?.let { it ->
            it.length
        }
    }
}