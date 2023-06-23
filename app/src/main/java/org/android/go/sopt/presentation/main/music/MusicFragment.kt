package org.android.go.sopt.presentation.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import org.android.go.sopt.databinding.FragmentMusicBinding
import org.android.go.sopt.presentation.main.music.MusicAdapter
import org.android.go.sopt.presentation.viewmodel.MusicViewModel

class MusicFragment : Fragment(), MusicAdapter.AdapterCallback {
    private val musicViewModel: MusicViewModel by viewModels()

    private var _binding: FragmentMusicBinding? = null
    private val binding: FragmentMusicBinding
        get() = requireNotNull(_binding) { "앗 ! _binding이 null이다 !" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMusicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gridLayoutManager = GridLayoutManager(context, 2);
        val musicAdapter = MusicAdapter(requireContext())
        musicViewModel.musicListLiveData.observe(
            viewLifecycleOwner,
            Observer { musicList ->
                musicAdapter.setMusicList(musicList)
            })
        musicViewModel.fetchMusicData("daehwan2yo")
        binding.musicGrid.adapter = musicAdapter
        binding.musicGrid.layoutManager = gridLayoutManager
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onFailure() {
        Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_SHORT).show()
    }
}