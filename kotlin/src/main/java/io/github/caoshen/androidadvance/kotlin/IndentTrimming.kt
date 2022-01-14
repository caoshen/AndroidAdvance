val world = "multiline world"

fun main() {
    //Hello
    //\multiline world
    println(
        """
            Hello
            \$world
        """.trimIndent()
    )

    //Hello
    //$world
    println(
        """
            Hello
            ${'$'}world
        """.trimIndent()
    )

    //Hello
    //multiline world
    println(
        """
            Hello
            $world
        """.trimIndent()
    )
}