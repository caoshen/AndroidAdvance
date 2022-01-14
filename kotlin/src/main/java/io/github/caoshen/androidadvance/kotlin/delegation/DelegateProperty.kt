package io.github.caoshen.androidadvance.kotlin.delegation

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class DelegateProperty : ReadWriteProperty<RealSubject, String> {
    override fun getValue(thisRef: RealSubject, property: KProperty<*>): String {
        return "$thisRef, delegate '${property.name}"
    }

    override fun setValue(thisRef: RealSubject, property: KProperty<*>, value: String) {
        println("$value has been assigned to ${property.name} in $thisRef.")
    }
}