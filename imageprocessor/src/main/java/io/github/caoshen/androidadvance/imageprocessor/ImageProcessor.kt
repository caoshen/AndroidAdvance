package io.github.caoshen.androidadvance.imageprocessor

import kotlinx.coroutines.runBlocking
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

const val BASE_PATH = "./imageprocessor/src/main/resources/images/"

/**
 * 加载本地图片
 */
fun loadImage(imageFile: File) =
    ImageIO.read(imageFile)
        .let {
            Array(it.height) { y ->
                Array(it.width) { x ->
                    Color(it.getRGB(x, y))
                }
            }
        }.let {
            Image(it)
        }

/**
 * 图片裁切
 * 待实现
 */
fun Image.crop(startY: Int, startX: Int, width: Int, height: Int): Image = let {
    Image(Array(height) { y ->
        Array(width) { x ->
            Color(it.getPixel(startY + y, startX + x).rgb)
        }
    })
}

/**
 * 横向翻转图片
 * 待实现
 */
fun Image.flipHorizontal(): Image = let {
    Image(Array(it.height()) { y ->
        Array(it.width()) { x ->
            Color(it.getPixel(y, it.width() - 1 - x).rgb)
        }
    })
}

/**
 * 纵向翻转图片
 * 待实现
 */
fun Image.flipVertical(): Image = let {
    Image(Array(it.height()) { y ->
        Array(it.width()) { x ->
            Color(it.getPixel(it.height() - 1 - y, x).rgb)
        }
    })
}

/**
 * 挂起函数，以http的方式下载图片，保存到本地
 * 待实现
 */
suspend fun downloadImage(url: String, outputFile: File): Boolean = TODO()

/**
 * 保存图片到本地
 */
fun Image.save(path: String) {
    val bufferedImage = BufferedImage(width(), height(), BufferedImage.TYPE_INT_RGB).apply {
        for (y in 0 until height()) {
            for (x in 0 until width()) {
                setRGB(x, y, getPixel(y, x).rgb)
            }
        }
    }
    ImageIO.write(bufferedImage, "png", File(path))
}

fun main() = runBlocking {
    println("${BASE_PATH}android.png")
    val image = loadImage(File("${BASE_PATH}android.png"))
    println("Width = ${image.width()};Height = ${image.height()}")

    println("flip horizontal")
    val imageFlipHorizontal = image.flipHorizontal()

    println("save image start")
    imageFlipHorizontal.save("${BASE_PATH}android-flip-horizontal.png")

    println("flip vertical")
    val imageFlipVertical = image.flipVertical()

    println("save image start")
    imageFlipVertical.save("${BASE_PATH}android-flip-vertical.png")

    println("crop")
    val imageCrop = image.crop(100, 100, image.width() / 2, image.height() / 2)

    println("save image start")
    imageCrop.save("${BASE_PATH}android-crop.png")
}