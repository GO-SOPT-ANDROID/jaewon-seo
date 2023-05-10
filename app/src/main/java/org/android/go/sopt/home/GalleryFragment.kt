package org.android.go.sopt.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.android.go.sopt.R
import org.android.go.sopt.adapter.GalleryAdapter
import org.android.go.sopt.databinding.FragmentGalleryBinding
import org.android.go.sopt.home.viewmodel.GalleryViewModel
import org.android.go.sopt.home.viewmodel.HomeViewModel
import org.android.go.sopt.sampledata.Repo

class GalleryFragment: Fragment() {
    val itemList : List<Int> = GalleryViewModel().getImgList() //galleryViewModel img 보관
    private var _binding: FragmentGalleryBinding? = null
    private val binding: FragmentGalleryBinding
        get() = requireNotNull(_binding) { "앗 ! _binding이 null이다 !" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //
        binding.pagerGallery.adapter = GalleryAdapter().apply {
            setItemList(itemList) //galleryViewModel에서 받아온 값 설정
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}