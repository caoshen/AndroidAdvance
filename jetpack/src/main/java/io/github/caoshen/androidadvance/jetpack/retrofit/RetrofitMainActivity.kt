package io.github.caoshen.androidadvance.jetpack.retrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.caoshen.androidadvance.jetpack.databinding.ActivityMainRetrofitBinding

/**
 * @author caoshen
 * @date 2021/1/27
 */
class RetrofitMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainRetrofitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainRetrofitBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)
    }
}