package io.github.caoshen.androidadvance.kotlin.delegation

/**
 * 委托映射的属性值
 *
 * 把一个属性委托给 map
 */
class User(val map: Map<String, Any?>) {
    val name: String by map

    val firstName: String
        get() = map["name"] as String
}