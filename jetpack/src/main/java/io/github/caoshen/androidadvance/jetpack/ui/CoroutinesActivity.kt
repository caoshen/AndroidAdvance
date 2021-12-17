package io.github.caoshen.androidadvance.jetpack.ui

import by.kirich1409.viewbindingdelegate.viewBinding
import io.github.caoshen.androidadvance.jetpack.R
import io.github.caoshen.androidadvance.jetpack.databinding.ActivityCoroutinesBinding
import io.github.caoshen.baselib.base.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread

class CoroutinesActivity : BaseActivity(R.layout.activity_coroutines) {

    private val mViewBinding by viewBinding(ActivityCoroutinesBinding::bind)

    override fun init() {
//        launchWithGlobalScopeDefault()
//        launchWithGlobalScopeMain()
//        launchWithThread()
//        launchWithThreadFun()

//        switchWithCoroutines()
        switchWithCallback()
    }

    private fun launchWithGlobalScopeDefault() {
        // I/System.out: Coroutines 1 DefaultDispatcher-worker-1
        GlobalScope.launch {
            println("Coroutines Camp 1 ${Thread.currentThread().name}")
        }
    }

    private fun launchWithGlobalScopeMain() {
        // I/System.out: Coroutines 1 main
        GlobalScope.launch(Dispatchers.Main) {
            println("Coroutines Camp 1 ${Thread.currentThread().name}")
            mViewBinding.textThread.setText("Coroutines 1 ${Thread.currentThread().name}")
        }
    }

    private fun launchWithThread() {
        // I/System.out: Coroutines Camp 2 Thread-3
        Thread {
            println("Coroutines Camp 2 ${Thread.currentThread().name}")
        }.start()
    }

    private fun launchWithThreadFun() {
        // I/System.out: Coroutines Camp 3 Thread-3
        thread {
            println("Coroutines Camp 3 ${Thread.currentThread().name}")
        }
    }

    private fun switchWithCallback() {
//    2021-12-17 15:14:48.762 16785-17971 I/System.out: Coroutines Camp io4 Thread-6
//    2021-12-17 15:14:48.822 16785-16785 I/System.out: Coroutines Camp ui4 main
//    2021-12-17 15:14:48.822 16785-17976 I/System.out: Coroutines Camp io5 Thread-7
//    2021-12-17 15:14:48.828 16785-16785 I/System.out: Coroutines Camp ui5 main
//    2021-12-17 15:14:48.829 16785-17977 I/System.out: Coroutines Camp io6 Thread-8
//    2021-12-17 15:14:48.833 16785-16785 I/System.out: Coroutines Camp ui6 main
        thread {
            ioCode4()
            runOnUiThread {
                uiCode4()
                thread {
                    ioCode5()
                    runOnUiThread {
                        uiCode5()
                        thread {
                            ioCode6()
                            runOnUiThread {
                                uiCode6()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun switchWithCoroutines() {
//        16001-16073 I/System.out: Coroutines Camp io1 DefaultDispatcher-worker-1
//        16001-16001 I/System.out: Coroutines Camp ui1 main
        GlobalScope.launch(Dispatchers.Main) {
            ioCode1()
            uiCode1()
        }
    }

    suspend fun ioCode1() {
        withContext(Dispatchers.IO) {
            println("Coroutines Camp io1 ${Thread.currentThread().name}")
        }
    }

    fun uiCode1() {
        println("Coroutines Camp ui1 ${Thread.currentThread().name}")
    }

    suspend fun ioCode2() {
        withContext(Dispatchers.IO) {
            println("Coroutines Camp io2 ${Thread.currentThread().name}")
        }
    }

    fun uiCode2() {
        println("Coroutines Camp ui2 ${Thread.currentThread().name}")
    }

    suspend fun ioCode3() {
        withContext(Dispatchers.IO) {
            println("Coroutines Camp io3 ${Thread.currentThread().name}")
        }
    }

    fun uiCode3() {
        println("Coroutines Camp ui3 ${Thread.currentThread().name}")
    }

    fun ioCode4() {
        println("Coroutines Camp io4 ${Thread.currentThread().name}")
    }

    fun uiCode4() {
        println("Coroutines Camp ui4 ${Thread.currentThread().name}")
    }

    fun ioCode5() {
        println("Coroutines Camp io5 ${Thread.currentThread().name}")
    }

    fun uiCode5() {
        println("Coroutines Camp ui5 ${Thread.currentThread().name}")
    }

    fun ioCode6() {
        println("Coroutines Camp io6 ${Thread.currentThread().name}")
    }

    fun uiCode6() {
        println("Coroutines Camp ui6 ${Thread.currentThread().name}")
    }
}