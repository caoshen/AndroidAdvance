package io.github.caoshen.baselib.base

import android.app.ProgressDialog
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.jeremyliao.liveeventbus.LiveEventBus
import io.github.caoshen.baselib.network.utils.SHOW_TOAST
import io.github.caoshen.baselib.utils.toast

abstract class BaseActivity(@LayoutRes contentLayoutId: Int) : AppCompatActivity(contentLayoutId),
    IUiView {

    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        observeToast()
    }

    protected abstract fun init()

    private fun observeToast() {
        LiveEventBus.get<String>(SHOW_TOAST).observe(this) {
            toast(it)
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