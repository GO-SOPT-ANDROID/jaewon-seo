package org.android.go.sopt.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePostMusicDto(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: Data
) {
    @Serializable
    data class Data(
        @SerialName("title")
        val title: String,
        @SerialName("singer")
        val singer: String,
        @SerialName("url")
        val url: String
    )
}