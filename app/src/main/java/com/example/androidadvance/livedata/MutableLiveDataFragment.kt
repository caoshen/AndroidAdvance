package com.example.androidadvance.livedata

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

/**
 * @author caoshen
 * @date 2020/12/3
 */
class MutableLiveDataFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getLiveDataA()
    }

    private val changeObserver = Observer<String> { value ->
        value?.let {

        }
    }
}