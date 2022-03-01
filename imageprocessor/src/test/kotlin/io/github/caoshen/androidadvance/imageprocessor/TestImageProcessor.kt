package io.github.caoshen.androidadvance.imageprocessor

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class TestImageProcessor {

    companion object {
        const val BASE_TEST_PATH = "./src/test/resources/images/"
        const val BASE_PATH = "./src/main/resources/images/"
    }

    /**
     * 待实现的单元测试
     */
    @Test
    fun testFlipHorizontal() {
        val image = loadImage(File("${BASE_PATH}android.png"))
        val flipped = image.flipHorizontal()
        val target = loadImage(File("${BASE_TEST_PATH}android_flipped.png"))

        checkImageSame(flipped, target)
    }

    /**
     * 待实现的单元测试
     */
    @Test
    fun testFlipVertical() {
        val image = loadImage(File("${BASE_PATH}android.png"))
        val flipped = image.flipVertical()
        val target = loadImage(File("${BASE_TEST_PATH}android_up_side_down.png"))

        checkImageSame(flipped, target)
    }

    /**
     * 待实现的单元测试
     */
    @Test
    fun testCrop() {
        val image = loadImage(File("${BASE_PATH}android.png"))
        val crop = image.crop(0, 0, image.width() / 2, image.height() / 2)
        val target = loadImage(File("${BASE_TEST_PATH}android_half_crop.png"))

        checkImageSame(crop, target)
    }

    private fun checkImageSame(picture: Image, expected: Image) {
        assertEquals(picture.height(), expected.height())
        assertEquals(picture.width(), expected.width())

        for (row in 0 until picture.height()) {
            for (column in 0 until picture.width()) {
                val actualPixel = picture.getPixel(row, column)
                val expectedPixel = expected.getPixel(row, column)
                assertEquals(actualPixel, expectedPixel)
            }
        }
    }
}