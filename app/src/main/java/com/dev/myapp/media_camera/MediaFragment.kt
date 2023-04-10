package com.dev.myapp.media_camera

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dev.myapp.R
import com.dev.myapp.databinding.FragmentAnimationBinding
import com.dev.myapp.databinding.FragmentMediaBinding
import java.io.IOException

class MediaFragment : Fragment() {
    private lateinit var binding: FragmentMediaBinding
    private lateinit var sp: SoundPool
    private var soundId: Int = 0
    private var spLoaded = false

    private var mMediaPlayer: MediaPlayer? = null
    private var isReady: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMediaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sp = SoundPool.Builder()
            .setMaxStreams(10)
            .build()
        sp.setOnLoadCompleteListener { _, _, status ->
            if (status == 0) {
                spLoaded = true
            } else {
                Toast.makeText(requireContext(), "Gagal load", Toast.LENGTH_SHORT).show()
            }
        }

        soundId = sp.load(requireContext(), R.raw.mysound, 1)

        binding.btnSoundpool.setOnClickListener {
            if (spLoaded) {
                sp.play(soundId, 100f, 100f, 0, 0, 1f)
            }
        }

        binding.btnMediaplayer.setOnClickListener {
            if (!isReady) {
                mMediaPlayer?.prepareAsync()
            } else {
                if (mMediaPlayer?.isPlaying as Boolean) {
                    mMediaPlayer?.pause()
                } else {
                    mMediaPlayer?.start()
                }
            }
        }

        binding.btnStopMediaplayer.setOnClickListener {
            if (mMediaPlayer?.isPlaying as Boolean || isReady) {
                mMediaPlayer?.stop()
                isReady = false
            }
        }

        initMediaPlayer()
    }

    private fun initMediaPlayer() {
        mMediaPlayer = MediaPlayer()
        val attribute = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        mMediaPlayer?.setAudioAttributes(attribute)
        val afd = requireContext().resources.openRawResourceFd(R.raw.mysound)
        try {
            mMediaPlayer?.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        mMediaPlayer?.setOnPreparedListener {
            isReady = true
            mMediaPlayer?.start()
        }
        mMediaPlayer?.setOnErrorListener { _, _, _ -> false }
    }
}