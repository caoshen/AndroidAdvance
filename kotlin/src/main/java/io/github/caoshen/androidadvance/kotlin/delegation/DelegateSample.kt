package io.github.caoshen.androidadvance.kotlin.delegation

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

fun main() {

    // delegate class
    UniversalDb(SqlDb()).save()
    UniversalDb(GreenDaoDb()).save()

    // delegate property

    // standard delegate

    // custom delegate property

    // provide delegate
}