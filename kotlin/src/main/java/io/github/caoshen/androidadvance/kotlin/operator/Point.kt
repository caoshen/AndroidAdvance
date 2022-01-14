package io.github.caoshen.androidadvance.kotlin.operator

data class Point(val x: Int, val y: Int) {
//    operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)
}


fun main() {
    val a = Point(1, 2)
    val b = Point(3, 4)
    // (4, 6) * (1, 2) / (3, 4) = (4, 12) / (3, 4) = (1, 3)
    println((a + b) * a / b)
}

operator fun Point.plus(other: Point) = Point(x + other.x, y + other.y)

operator fun Point.times(other: Point) =
    Point(x * other.x, y * other.y)

operator fun Point.div(other: Point) =
    Point(x / other.x, y / other.y)

operator fun Point.minus(other: Point) =
    Point(x - other.x, y - other.y)

operator fun Point.rem(other: Point) =
    Point(x % other.x, y % other.y)