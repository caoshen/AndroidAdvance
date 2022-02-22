package io.github.caoshen.androidadvance.kotlin.generics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity<SampleActivity>()
    }
}