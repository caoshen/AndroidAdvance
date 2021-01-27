package io.github.caoshen.androidadvance.jetpack

/**
 * @author caoshen
 * @date 2021/1/13
 */
class ListWithTrash<T>(private val innerList: MutableList<T> = ArrayList<T>()) :
        MutableCollection<T> by innerList {

    var deletedItem: T? = null

    override fun remove(element: T): Boolean {
        deletedItem = element
        return innerList.remove(element)
    }

    fun recover(): T? {
        return deletedItem
    }
}