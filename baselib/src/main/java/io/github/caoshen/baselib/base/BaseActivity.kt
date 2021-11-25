package io.github.caoshen.baselib.base

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.jeremyliao.liveeventbus.LiveEventBus
import io.github.caoshen.baselib.network.utils.SHOW_TOAST
import io.github.caoshen.baselib.utils.toast

abstract class BaseActivity(@LayoutRes contentLayoutId: Int) : AppCompatActivity(contentLayoutId),
    IUiView {

    companion object {
        private const val TAG = "BaseActivity"
    }

    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        observeToast()
        Log.d(TAG, "onCreate")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "onRestoreInstanceState")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
    }

    protected abstract fun init()

    private fun observeToast() {
        LiveEventBus.get<String>(SHOW_TOAST).observe(this) {
            toast("$it in ${javaClass.simpleName}")
        }
    }

    override fun showLoading() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this)
        }
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.takeIf {
            it.isShowing
        }?.dismiss()
    }
}