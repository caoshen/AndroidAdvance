package io.github.caoshen.androidadvance.jetpack.ui

import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import io.github.caoshen.androidadvance.jetpack.R
import io.github.caoshen.androidadvance.jetpack.databinding.ActivityCoroutinesBinding
import io.github.caoshen.androidadvance.jetpack.model.Api
import io.github.caoshen.androidadvance.jetpack.model.Repo
import io.github.caoshen.baselib.base.BaseActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class CoroutinesActivity : AppCompatActivity(R.layout.activity_coroutines) {

//    private val mViewBinding by viewBinding(ActivityCoroutinesBinding::bind)

//    override fun init() {
//        launchWithGlobalScopeDefault()
//        launchWithGlobalScopeMain()
//        launchWithThread()
//        launchWithThreadFun()

//        switchWithCoroutines()
//        switchWithCallback()
//        ioCodeThreadPool()
//        retrofitRx()
//        retrofitCoroutines()
//        SystemClock.sleep(60 * 1000L)
//    }



//    private fun launchWithGlobalScopeDefault() {
//        // I/System.out: Coroutines 1 DefaultDispatcher-worker-1
//        GlobalScope.launch {
//            println("Coroutines Camp 1 ${Thread.currentThread().name}")
//        }
//    }
//
//    private fun launchWithGlobalScopeMain() {
//        // I/System.out: Coroutines 1 main
//        GlobalScope.launch(Dispatchers.Main) {
//            println("Coroutines Camp 1 ${Thread.currentThread().name}")
//            mViewBinding.textThread.setText("Coroutines 1 ${Thread.currentThread().name}")
//        }
//    }
//
//    private fun launchWithThread() {
//        // I/System.out: Coroutines Camp 2 Thread-3
//        Thread {
//            println("Coroutines Camp 2 ${Thread.currentThread().name}")
//        }.start()
//    }
//
//    private fun launchWithThreadFun() {
//        // I/System.out: Coroutines Camp 3 Thread-3
//        thread {
//            println("Coroutines Camp 3 ${Thread.currentThread().name}")
//        }
//    }
//
//    private fun switchWithCallback() {
////    2021-12-17 15:14:48.762 16785-17971 I/System.out: Coroutines Camp io4 Thread-6
////    2021-12-17 15:14:48.822 16785-16785 I/System.out: Coroutines Camp ui4 main
////    2021-12-17 15:14:48.822 16785-17976 I/System.out: Coroutines Camp io5 Thread-7
////    2021-12-17 15:14:48.828 16785-16785 I/System.out: Coroutines Camp ui5 main
////    2021-12-17 15:14:48.829 16785-17977 I/System.out: Coroutines Camp io6 Thread-8
////    2021-12-17 15:14:48.833 16785-16785 I/System.out: Coroutines Camp ui6 main
//        thread {
//            ioCode4()
//            runOnUiThread {
//                uiCode4()
//                thread {
//                    ioCode5()
//                    runOnUiThread {
//                        uiCode5()
//                        thread {
//                            ioCode6()
//                            runOnUiThread {
//                                uiCode6()
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    private fun switchWithCoroutines() {
////        16001-16073 I/System.out: Coroutines Camp io1 DefaultDispatcher-worker-1
////        16001-16001 I/System.out: Coroutines Camp ui1 main
//        GlobalScope.launch(Dispatchers.Main) {
//            ioCode1()
//            uiCode1()
//        }
//    }
//
//    suspend fun ioCode1() {
//        withContext(Dispatchers.IO) {
//            println("Coroutines Camp io1 ${Thread.currentThread().name}")
//        }
//    }
//
//    fun uiCode1() {
//        println("Coroutines Camp ui1 ${Thread.currentThread().name}")
//    }
//
//    suspend fun ioCode2() {
//        withContext(Dispatchers.IO) {
//            println("Coroutines Camp io2 ${Thread.currentThread().name}")
//        }
//    }
//
//    fun uiCode2() {
//        println("Coroutines Camp ui2 ${Thread.currentThread().name}")
//    }
//
//    suspend fun ioCode3() {
//        withContext(Dispatchers.IO) {
//            println("Coroutines Camp io3 ${Thread.currentThread().name}")
//        }
//    }
//
//    fun uiCode3() {
//        println("Coroutines Camp ui3 ${Thread.currentThread().name}")
//    }
//
//    fun ioCode4() {
//        println("Coroutines Camp io4 ${Thread.currentThread().name}")
//    }
//
//    fun uiCode4() {
//        println("Coroutines Camp ui4 ${Thread.currentThread().name}")
//    }
//
//    fun ioCode5() {
//        println("Coroutines Camp io5 ${Thread.currentThread().name}")
//    }
//
//    fun uiCode5() {
//        println("Coroutines Camp ui5 ${Thread.currentThread().name}")
//    }
//
//    fun ioCode6() {
//        println("Coroutines Camp io6 ${Thread.currentThread().name}")
//    }
//
//    fun uiCode6() {
//        println("Coroutines Camp ui6 ${Thread.currentThread().name}")
//    }
//
//    fun ioCodeThreadPool() {
////        2021-12-21 10:22:35.192 20399-20508 I/System.out: Coroutines Camp classic io1 pool-4-thread-1
////        2021-12-21 10:22:35.192 20399-20509 I/System.out: Coroutines Camp classic io1 pool-4-thread-2
////        2021-12-21 10:22:35.234 20399-20399 I/System.out: Coroutines Camp ui1 main
//        classicIoCode1(false) {
//            classicIoCode1 {
//                uiCode1()
//            }
//        }
//    }
//
//    private val executor = ThreadPoolExecutor(
//        5, 20, 1, TimeUnit.MINUTES,
//        LinkedBlockingQueue()
//    )
//
//    fun classicIoCode1(uiThread: Boolean = true, block: () -> Unit) {
//        executor.execute() {
//            println("Coroutines Camp classic io1 ${Thread.currentThread().name}")
//            if (uiThread) {
//                runOnUiThread {
//                    block()
//                }
//            } else {
//                block()
//            }
//        }
//    }
//
//    /**
//     * retrofit coroutines.
//     * bad example
//     */
//    fun retrofitCoroutines() {
//        GlobalScope.launch(Dispatchers.Main) {
//            try {
//                val retrofit = Retrofit.Builder()
//                    .baseUrl("https://api.github.com/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//                val api = retrofit.create(Api::class.java)
//
//                val start = System.currentTimeMillis()
//                // background. NOT do this in Dispatchers Main
//                val repos = api.listReposKt("rengwuxian")
//                val end = System.currentTimeMillis()
////                2021-12-21 15:24:31.638 29760-29760 I/System.out: list repos kt cost:1693 ms in main
//                println("list repos kt cost:${end - start} ms in ${Thread.currentThread().name}")
//
//                // foreground
//                mViewBinding.textThread.text = repos[0].name
//            } catch (e: Exception) {
//                mViewBinding.textThread.text = e.message
//            }
//        }
//    }
//
//    /**
//     * retrofit rxjava3
//     */
//    fun retrofitRx() {
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://api.github.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
//            .build()
//
//        val api = retrofit.create(Api::class.java)
//        api.listRepoRx("rengwuxian")
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : SingleObserver<List<Repo>> {
//                override fun onSubscribe(d: Disposable?) {
//                }
//
//                override fun onSuccess(t: List<Repo>?) {
//                    // AndroidSwipeLayout
//                    mViewBinding.textThread.text = t?.get(0)?.name
//                }
//
//                override fun onError(e: Throwable?) {
//                    mViewBinding.textThread.text = e?.message
//                }
//            })
//    }
}