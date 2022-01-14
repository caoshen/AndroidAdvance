// 返回 lambda 表达式
fun hello() = {
    println("Hello, World")
}

// 改成普通函数
fun hello2() {
    println("Hello, World")
}

fun main() {
    // hello() 返回的是一个 lambda 函数，只有调用这个函数才会执行 lambda 里面的内容
    // Nothing
    hello()
    // Hello, World
    hello()()
    // Hello, World
    hello2()

}