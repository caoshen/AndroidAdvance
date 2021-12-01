package io.github.caoshen.androidadvance.jetpack.ui

import androidx.appcompat.app.AppCompatActivity
import android.media.AudioManager

import android.media.AudioManager.OnAudioFocusChangeListener
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import io.github.caoshen.androidadvance.jetpack.R


class AudioFocusActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "AudioFocusActivity"
    }

    /*
     * AUDIOFOCUS_GAIN -> AUDIOFOCUS_LOSS
     * AUDIOFOCUS_GAIN_TRANSIENT <-> AUDIOFOCUS_LOSS_TRANSIENT
     * AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE <-> AUDIOFOCUS_LOSS_TRANSIENT
     * AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK <-> AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK
     */
    private lateinit var button1: Button

    private lateinit var button2: Button

    private lateinit var audioManager: AudioManager

    private val afChangeListener1 =
        OnAudioFocusChangeListener { focusChange ->
            when (focusChange) {
                AudioManager.AUDIOFOCUS_GAIN -> Log.d(TAG, "afChangeListener1>> AUDIOFOCUS_GAIN")
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT -> Log.d(
                    TAG,
                    "afChangeListener1>> AUDIOFOCUS_GAIN_TRANSIENT"
                )
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE -> Log.d(
                    TAG,
                    "afChangeListener1>> AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE"
                )
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK -> Log.d(
                    TAG,
                    "afChangeListener1>> AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK"
                )
                AudioManager.AUDIOFOCUS_LOSS -> Log.d(TAG, "afChangeListener1>> AUDIOFOCUS_LOSS")
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> Log.d(
                    TAG,
                    "afChangeListener1>> AUDIOFOCUS_LOSS_TRANSIENT"
                )
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> Log.d(
                    TAG,
                    "afChangeListener1>> AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK"
                )
                else -> Log.d(TAG, "afChangeListener1>> default")
            }
        }
    private val afChangeListener2 =
        OnAudioFocusChangeListener { focusChange ->
            when (focusChange) {
                AudioManager.AUDIOFOCUS_GAIN -> Log.d(TAG, "afChangeListener2>> AUDIOFOCUS_GAIN")
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT -> Log.d(
                    TAG,
                    "afChangeListener2>> AUDIOFOCUS_GAIN_TRANSIENT"
                )
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE -> Log.d(
                    TAG,
                    "afChangeListener2>> AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE"
                )
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK -> Log.d(
                    TAG,
                    "afChangeListener2>> AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK"
                )
                AudioManager.AUDIOFOCUS_LOSS -> Log.d(TAG, "afChangeListener2>> AUDIOFOCUS_LOSS")
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> Log.d(
                    TAG,
                    "afChangeListener2>> AUDIOFOCUS_LOSS_TRANSIENT"
                )
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> Log.d(
                    TAG,
                    "afChangeListener2>> AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK"
                )
                else -> Log.d(TAG, "afChangeListener2>> default")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        grantPermission()
    }

    private fun grantPermission() {
        audioManager = getSystemService(AUDIO_SERVICE) as AudioManager
//        button1 = findViewById<View>(R.id.button1) as Button
//        button1.setOnClickListener(this)
//        button2 = findViewById<View>(R.id.button2) as Button
//        button2.setOnClickListener(this)
    }

//    fun onClick(v: View) {
//        var result = AudioManager.AUDIOFOCUS_REQUEST_FAILED
//        when (v.getId()) {
//            R.id.button1 -> {
//                result = audioManager!!.requestAudioFocus(
//                    afChangeListener1,
//                    AudioManager.STREAM_MUSIC,
//                    AudioManager.AUDIOFOCUS_GAIN
//                )
//                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
//                    afChangeListener1.onAudioFocusChange(AudioManager.AUDIOFOCUS_GAIN)
//                }
//            }
//            R.id.button2 -> {
//                result = audioManager!!.requestAudioFocus(
//                    afChangeListener2,
//                    AudioManager.STREAM_MUSIC,
//                    AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
//                )
//                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
//                    afChangeListener2.onAudioFocusChange(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
//                }
//                result = audioManager!!.abandonAudioFocus(afChangeListener2)
//                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
//                    afChangeListener2.onAudioFocusChange(AudioManager.AUDIOFOCUS_LOSS)
//                }
//            }
//            else -> {
//            }
//        }
//    }
}