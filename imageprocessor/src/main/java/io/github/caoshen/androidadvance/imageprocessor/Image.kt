package io.github.caoshen.androidadvance.imageprocessor

import java.awt.Color

class Image(private val pixels: Array<Array<Color>>) {

    fun height(): Int {
        return pixels.size
    }

    fun width(): Int {
        return pixels[0].size
    }

    /**
     * 底层不处理越界
     */
    fun getPixel(y: Int, x: Int): Color {
        return pixels[y][x]
    }
}
