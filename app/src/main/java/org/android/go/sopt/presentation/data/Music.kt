package org.android.go.sopt.presentation.data

import okhttp3.MultipartBody

data class Music(
    val image: MultipartBody.Part,
    val title: String,
    val singer: String
)