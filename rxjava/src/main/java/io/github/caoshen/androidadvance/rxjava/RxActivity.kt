package io.github.caoshen.androidadvance.rxjava

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import io.github.caoshen.androidadvance.rxjava.databinding.ActivityRxBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * @author caoshen
 * @date 2020/12/2
 */
class RxActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val TAG = "RxActivity"
    }

    private var disposable: Disposable? = null

    private lateinit var compositeDisposable: CompositeDisposable

    private lateinit var binding: ActivityRxBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRxBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        compositeDisposable = CompositeDisposable()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_interval -> {
                disposable = interval(1, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { t ->
                            Log.d(TAG, "onClick: interval:$t")
                            binding.textCount.text = t.toString()
                        }
                compositeDisposable.add(disposable!!)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}