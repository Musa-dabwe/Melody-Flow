package com.musa.melodyflow.ui.playlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.musa.melodyflow.databinding.FragmentPlaylistBinding

class PlaylistFragment : Fragment() {

    private var _binding: FragmentPlaylistBinding? = null
    private val binding get() = _binding!!

    // private lateinit var playlistAdapter: PlaylistAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaylistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        binding.fabAddPlaylist.setOnClickListener {
            // Handle new playlist creation
        }
    }

    private fun setupRecyclerView() {
        // playlistAdapter = PlaylistAdapter()
        // binding.recyclerViewPlaylists.adapter = playlistAdapter
        // Load data into adapter
        // (e.g., viewModel.playlists.observe(viewLifecycleOwner) { playlists ->
        // playlistAdapter.submitList(playlists)
        // })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
