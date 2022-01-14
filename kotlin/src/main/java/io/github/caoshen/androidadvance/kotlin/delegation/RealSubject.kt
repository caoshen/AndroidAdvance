package io.github.caoshen.androidadvance.kotlin.delegation

import kotlin.properties.Delegates

class RealSubject : Subject by Delegate() {

    private var name: String by DelegateProperty()

    private val loadingDialog by lazy {

    }

    private var lastName: String by Delegates.observable("<no name>") { property, oldValue, newValue ->
        println("$oldValue -> $newValue")
    }
}