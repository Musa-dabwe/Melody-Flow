package com.musa.melodyflow.ui.nowplaying

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.musa.melodyflow.databinding.FragmentNowPlayingBinding

class NowPlayingFragment : Fragment() {

    private var _binding: FragmentNowPlayingBinding? = null
    private val binding get() = _binding!!

    // private lateinit var mediaBrowser: MediaBrowserCompat
    // private var mediaController: MediaControllerCompat? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNowPlayingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
        // connectToMediaService()
    }

    private fun setupClickListeners() {
        binding.buttonPlayPause.setOnClickListener {
            // mediaController?.transportControls?.play() or pause()
        }

        binding.buttonNext.setOnClickListener {
            // mediaController?.transportControls?.skipToNext()
        }

        binding.buttonPrevious.setOnClickListener {
            // mediaController?.transportControls?.skipToPrevious()
        }
    }

    // private fun connectToMediaService() {
    // // Logic to connect to MediaBrowserService
    // }

    // private fun updateUI(metadata: MediaMetadataCompat?, playbackState: PlaybackStateCompat?) {
    // // Update title, artist, album art, progress, and play/pause button state
    // }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
