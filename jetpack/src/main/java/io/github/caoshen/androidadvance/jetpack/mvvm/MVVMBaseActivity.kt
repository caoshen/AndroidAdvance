package io.github.caoshen.androidadvance.jetpack.mvvm

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import io.github.caoshen.androidadvance.jetpack.mvvm.base.BaseActivity

abstract class MVVMBaseActivity<V : ViewBinding, VM : BaseViewModel> : BaseActivity() {

    private var mViewBinding: ViewBinding? = null
    private var mViewModel: BaseViewModel? = null

    abstract fun createViewBinding(): V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onPrepare()
        mViewBinding = createViewBinding()
        setContentView(mViewBinding?.root)

    }

    protected open fun onPrepare() {
    }
}