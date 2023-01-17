package com.example.nativelib

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * 应用与系统稳定性第七篇--- 用Asan 提前解决NDK疑难crash
 * https://www.jianshu.com/p/2addc08cb84b
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

//        findViewById<TextView>(R.id.hello_text).text = getStr()
    }

    fun hookAll(view: View) {
        NativeHacker.hook(2)
    }

//    external fun stringFromJNI(): String

    // 使用addr2line找行号
    /*
    ─$ addr2line -Cife /Users/wangjing/study/Asan/AddressSanitize/app/build/intermediates/cmake/debug/obj/arm64-v8a/libnative-lib.so  0xe738
Java_com_jingxun_asan_test_MainActivity_getStr
/Users/wangjing/study/Asan/AddressSanitize/app/.externalNativeBuild/cmake/debug/arm64-v8a/../../../../src/main/cpp/native-lib.cpp:16
     */
//    external fun getStr(): String

//    companion object {
//        init {
//            System.loadLibrary("native-lib")
//        }
//    }
    // Asan 报错
    // ERROR: AddressSanitizer: heap-buffer-overflow on address 0x003c89576c20 at pc 0x006f24d65cec bp 0x007fdc5cdb10 sp 0x007fdc5cdb08

    // 出错原因：写入到size=4的地址
    // I/wrap.sh: [1m[0m[1m[34mWRITE of size 4 at 0x003c89576c20 thread T0 (ample.nativelib)[1m[0m

    //出错代码的地址libnative-lib.so+0x1dce8
//    I/wrap.sh:     #0 0x6f24d65ce8  (/data/app/~~nQEZqsqW0-fh9Dq7ShAgxg==/com.example.nativelib-6fbigxZ3WpMM76Fr8mkQWw==/lib/arm64/libnative-lib.so+0x1dce8)

}