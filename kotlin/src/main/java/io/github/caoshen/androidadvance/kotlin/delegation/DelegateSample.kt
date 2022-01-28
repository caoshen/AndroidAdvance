package io.github.caoshen.androidadvance.kotlin.delegation

import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 委托类
 */
interface Db {
    fun save()
}

class SqlDb : Db {
    override fun save() {
        println("save sql db")
    }
}

class GreenDaoDb : Db {
    override fun save() {
        println("save green dao db")
    }
}

class UniversalDb(db: Db) : Db by db

/**
 * 委托属性
 * 两个属性之间的直接委托。Kotlin 1.4新特性
 */
class Item {
    var count: Int = 0
    var total: Int by ::count
}

/**
 * 懒加载委托
 */
val data: String by lazy {
    request()
}

fun request(): String {
    println("执行网络请求")
    return "网络数据"
}

/**
 * 自定义委托
 */
class StringDelegate(private var s: String = "Hello") {

    /**
     * getValue 操作符
     * thisRef 属性所在的类的类型
     * KProperty 表示一个属性，例如命名的 val 或者 var 声明。此类的实例可由 :: 运算符获取
     * 返回值 String
     */
    operator fun getValue(thisRef: Owner, property: KProperty<*>): String {
        return s
    }

    /**
     * setValue 操作符
     * thisRef 属性所在的类的类型
     * KProperty 表示一个属性，例如命名的 val 或者 var 声明。此类的实例可由 :: 运算符获取
     * value 属性的值，类型为 String
     */
    operator fun setValue(thisRef: Owner, property: KProperty<*>, value: String) {
        s = value
    }
}

class Owner {
    var log by SmartDelegator()
    var cat by SmartDelegator()
    var text: String by StringDelegate()
    var textReadWrite: String by StringReadWritePropertyDelegate()
}

/**
 * 使用 ReadWriteProperty接口实现委托属性，使用了泛型
 */
class StringReadWritePropertyDelegate(private var s: String = "Hello custom") :
    ReadWriteProperty<Owner, String> {
    override fun getValue(thisRef: Owner, property: KProperty<*>): String = s

    override fun setValue(thisRef: Owner, property: KProperty<*>, value: String) {
        s = value
    }
}

class SmartDelegator : PropertyDelegateProvider<Owner, StringDelegate> {
    override fun provideDelegate(thisRef: Owner, property: KProperty<*>): StringDelegate {
        return if (property.name.contains("log")) {
            StringDelegate("log")
        } else {
            StringDelegate("cat")
        }
    }
}

var c = 3

var cp = ::c

fun printProperty() {
    println(::c.get())
    println(::c.name)
    println(c.javaClass)
    println(c.javaClass.kotlin)
    println(c::class.java)
    println(c::class)
    println(cp::class)
    println(::c::class)
    println(::c::class.java)
}

fun main() {

    // delegate class
    println("\ndelegate class")
    UniversalDb(SqlDb()).save()
    UniversalDb(GreenDaoDb()).save()

    // delegate property
    println("\ndelegate property")
    val item = Item()
    item.count = 2
    println("total:${item.total}")

    // lazy delegate
    println("\nlazy delegate")
    println("开始")
    println(data)
    println(data)

    // custom delegate property
    println("\ncustom property delegate")
    val owner = Owner()
    println(owner.text)
    println(owner.textReadWrite)

    // provide delegate
    println(owner.log)
    println(owner.cat)
}
