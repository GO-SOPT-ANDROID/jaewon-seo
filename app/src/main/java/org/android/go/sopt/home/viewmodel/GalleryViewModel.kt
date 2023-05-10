package org.android.go.sopt.home.viewmodel

import androidx.lifecycle.ViewModel
import org.android.go.sopt.R

class GalleryViewModel : ViewModel() {

    private val imgList: List<Int> = listOf(
    R.drawable.sopt_logo,
    R.drawable.sopt_logo,
    R.drawable.sopt_logo,
    R.drawable.sopt_logo,
    R.drawable.sopt_logo
    )

    fun getImgList(): List<Int> = imgList
}

