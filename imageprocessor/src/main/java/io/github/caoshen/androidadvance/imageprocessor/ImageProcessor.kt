package io.github.caoshen.androidadvance.imageprocessor

import kotlinx.coroutines.*
import okhttp3.*
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import java.lang.Exception
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit
import javax.imageio.ImageIO
import kotlin.coroutines.CoroutineContext

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
 */
fun Image.crop(startY: Int, startX: Int, width: Int, height: Int): Image {
    val pixels = Array(height) { y ->
        Array(width) { x ->
            getPixel(startY + y, startX + x)
        }
    }
    return Image(pixels)
}

/**
 * 横向翻转图片
 */
fun Image.flipHorizontal(): Image {
    val pixels = Array(height()) { y ->
        Array(width()) { x ->
            getPixel(y, width() - 1 - x)
        }
    }
    return Image(pixels)
}

/**
 * 纵向翻转图片
 */
fun Image.flipVertical(): Image {
    val pixels = Array(height()) { y ->
        Array(width()) { x ->
            getPixel(height() - 1 - y, x)
        }
    }
    return Image(pixels)
}

/**
 * 挂起函数，以http的方式下载图片，保存到本地
 * 待实现
 */
suspend fun downloadImage(
    coroutineContext: CoroutineContext = Dispatchers.IO,
    url: String,
    outputFile: File
): Boolean {
    return withContext(coroutineContext) {
        try {
            downloadSync(url, outputFile)
        } catch (e: Exception) {
            println(e)
            return@withContext false
        }
        return@withContext true
    }
}

fun downloadSync(url: String, outputFile: File) {
    val request = Request.Builder()
        .url(url)
        .build()

    val client = OkHttpClient.Builder()
        .connectTimeout(10L, TimeUnit.SECONDS)
        .readTimeout(10L, TimeUnit.SECONDS)
        .build()

    val response = client.newCall(request)
        .execute()

    val body = response.body
    val code = response.code

    if (code >= HttpURLConnection.HTTP_OK && code < HttpURLConnection.HTTP_MULT_CHOICE && body != null) {
        body.byteStream().apply {
            outputFile.outputStream().use { fileOut ->
                copyTo(fileOut)
            }
        }
    }
}

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

fun Image.writeToFile(outputFile: File): Boolean = try {
    val width = width()
    val height = height()
    val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB).apply {
        for (x in 0 until width)
            for (y in 0 until height) {
                val pixel = getPixel(y, x)
                setRGB(x, y, pixel.rgb)
            }
    }
    ImageIO.write(image, "png", outputFile)
    true
} catch (e: Exception) {
    println(e)
    false
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
    val imageCrop = image.crop(0, 0, image.width() / 2, image.height() / 2)

    println("save image start")
    imageCrop.save("${BASE_PATH}android-crop.png")

    // download image
    println("start download png from network")
    val url = "https://kotlinlang.org/assets/images/index/banners/kotlin-1.6.20-M1.png"
    val path = "${BASE_PATH}downloaded.png"

    downloadImage(url = url, outputFile = File(path))

    loadImage(File(path))
        .flipVertical()
        .writeToFile(File("${BASE_PATH}download_flip_vertical.png"))

    println("Done")
}