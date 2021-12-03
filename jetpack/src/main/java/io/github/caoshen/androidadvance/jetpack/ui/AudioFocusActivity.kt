package io.github.caoshen.androidadvance.jetpack.ui

import androidx.appcompat.app.AppCompatActivity
import android.media.AudioManager

import android.media.AudioManager.OnAudioFocusChangeListener
import android.os.Bundle
import android.util.Log
import android.view.View
import io.github.caoshen.androidadvance.jetpack.R
import io.github.caoshen.androidadvance.jetpack.databinding.ActivityAudioFocusBinding


class AudioFocusActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        private const val TAG = "AudioFocusActivity"
    }

    private lateinit var binding: ActivityAudioFocusBinding

    /*
     * AUDIOFOCUS_GAIN -> AUDIOFOCUS_LOSS
     * AUDIOFOCUS_GAIN_TRANSIENT <-> AUDIOFOCUS_LOSS_TRANSIENT
     * AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE <-> AUDIOFOCUS_LOSS_TRANSIENT
     * AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK <-> AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK
     */
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
        binding = ActivityAudioFocusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        grantPermission()
    }

    private fun grantPermission() {
        audioManager = getSystemService(AUDIO_SERVICE) as AudioManager

        binding.btn1.setOnClickListener(this)
        binding.btn2.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        var result: Int
        when (v.id) {
            R.id.btn_1 -> {
                result = audioManager.requestAudioFocus(
                    afChangeListener1,
                    AudioManager.STREAM_MUSIC,
                    AudioManager.AUDIOFOCUS_GAIN
                )
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    afChangeListener1.onAudioFocusChange(AudioManager.AUDIOFOCUS_GAIN)
                }
            }

            R.id.btn_2 -> {
                result = audioManager.requestAudioFocus(
                    afChangeListener2,
                    AudioManager.STREAM_MUSIC,
                    AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
                )
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    afChangeListener2.onAudioFocusChange(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
                }

//                // abandon audio request
//                result = audioManager.abandonAudioFocus(afChangeListener2)
//                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
//                    afChangeListener2.onAudioFocusChange(AudioManager.AUDIOFOCUS_LOSS)
//                }

            }

            else -> {
                Log.e(TAG, "unknown view.")
            }
        }
    }
}