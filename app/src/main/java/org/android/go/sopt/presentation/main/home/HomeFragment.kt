package org.android.go.sopt.presentation.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.presentation.viewmodel.FollowerViewModel

class HomeFragment : Fragment(), MyAdapter.AdapterCallback {
    private val followerViewModel: FollowerViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "binding is null" }

    override fun onFailure() { //reqres 통신 실패시 Toast message
        Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myAdapter = MyAdapter(requireContext())

        followerViewModel.followerListLiveData.observe(
            viewLifecycleOwner,
            Observer { followerList ->
                myAdapter.setFollowerList(followerList)
            })

        val concapAdapter = ConcatAdapter(TitleAdapter(requireContext()), myAdapter)

        binding.fcvRv.adapter = concapAdapter
        binding.fcvRv.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
