package io.github.caoshen.androidadvance.jetpack.compose

import android.util.Log

/**
 * 默认参数
 */
class DefaultParam {
    companion object {
        private const val TAG = "DefaultParam"
    }

    fun test() {
        // 方法最后参数提供了默认值，签名参数不需要指定形参名称 b，直接传值
        test1(0)

        // 命名参数可以忽略传参顺序
        test1(
            b = "test",
            a = 2
        )

        // 构造方法第一个参数提供了默认值，第二个参数还是需要指定形参名称 b
        Test(b = 2)
    }

    // 默认参数
    fun test1(a: Int, b: String = "") {
        Log.e(TAG, "test1: a:$a b:$b")
    }

    // 默认参数数据类
    data class Test(val a: Int = 0, val b: Int)

    // 解构数据类，得到 a 和 b
    fun testData() {
        val test = Test(b = 2)
        val (a, b) = test
        Log.e(TAG, "a=$a")
        Log.e(TAG, "b=$b")
    }
}

/**
 * 高阶函数
 */
class HigherFunction {
    companion object {
        private const val TAG = "HigherFunction"

        fun test() {
            high({
                Log.e(TAG, "test: string: $it")
            }, "test")
        }

        fun high(one: (String) -> Unit, string: String) {
            one(string)
//            one.invoke(string)
        }

        fun high2(two: () -> Unit) {
            two()
        }

        fun test2() {
            high2 {
                Log.e(TAG, "test2: string")
            }
        }
    }
}

