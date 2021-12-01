package io.github.caoshen.androidadvance.jetpack.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import io.github.caoshen.androidadvance.jetpack.databinding.ActivityNameBinding
import io.github.caoshen.androidadvance.jetpack.viewmodel.NameViewModel
import io.github.caoshen.baselib.utils.startActivity

class NameActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "NameActivity"
    }

    private val nameViewModel by viewModels<NameViewModel>()

    private lateinit var binding: ActivityNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        nameViewModel.currentName.observe(this) { newName ->
//            binding.tvName.text = newName
            Log.d(TAG, "new name:$newName")
            startActivity<FastMainActivity>()
        }

        binding.btnClick.setOnClickListener {
//            nameViewModel.onUserClick()
            nameViewModel.currentName.value = "hello:${System.currentTimeMillis()}"
        }
    }
}