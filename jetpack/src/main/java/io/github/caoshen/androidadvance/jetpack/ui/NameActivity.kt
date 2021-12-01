package io.github.caoshen.androidadvance.jetpack.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import io.github.caoshen.androidadvance.jetpack.databinding.ActivityNameBinding
import io.github.caoshen.androidadvance.jetpack.viewmodel.NameViewModel

class NameActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "NameActivity"
    }

    private val nameViewModel by viewModels<NameViewModel>()

    private lateinit var binding: ActivityNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        binding = ActivityNameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        nameViewModel.currentName.observe(this) { newName ->
            binding.tvName.text = newName
            Log.d(TAG, "new name:$newName")
        }

        binding.btnClick.setOnClickListener {
            nameViewModel.onUserClick()
        }

        // stock livedata sample
        nameViewModel.stockLiveData.observe(this) { price ->
            Log.d(TAG, "price:$price")
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }
}