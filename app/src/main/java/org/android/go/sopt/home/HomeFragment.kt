package org.android.go.sopt.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import org.android.go.sopt.MyAdapter
import org.android.go.sopt.TitleAdapter
import org.android.go.sopt.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val viewModel : HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "앗 ! _binding이 null이다 !" }

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

        //val myAdapter = MyAdapter()

        val concapAdapter = ConcatAdapter(TitleAdapter(requireContext()), MyAdapter())
        binding.fcvRv.adapter = concapAdapter
        binding.fcvRv.layoutManager = LinearLayoutManager(context)
    }
    fun scrollToTop() {
        binding.fcvRv.scrollToPosition(0)
    }
    override fun onDestroyView() {
        _binding = null // 뷰를 제거하기 전에 null 처리를 해주는 것이 좋다.
        super.onDestroyView()
    }
}