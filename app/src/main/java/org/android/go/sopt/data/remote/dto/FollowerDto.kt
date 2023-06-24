package org.android.go.sopt.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.android.go.sopt.presentation.data.Follower

@Serializable
data class ResponseFollowerDto(
    @SerialName("data")
    val datas: List<Data>,
    @SerialName("page")
    val page: Int,
    @SerialName("per_page")
    val per_page: Int,
    @SerialName("support")
    val support: Support,
    @SerialName("total")
    val total: Int,
    @SerialName("total_pages")
    val total_pages: Int
) {
    @Serializable
    data class Data(
        @SerialName("avatar")
        val avatar: String,
        @SerialName("email")
        val email: String,
        @SerialName("first_name")
        val first_name: String,
        @SerialName("id")
        val id: Int,
        @SerialName("last_name")
        val last_name: String
    )

    @Serializable
    data class Support(
        @SerialName("text")
        val text: String,
        @SerialName("url")
        val url: String
    )

    fun toFollowerList() :List<Follower> = datas.map { follwer ->
        Follower(
            avatar = follwer.avatar,
            first_name = follwer.first_name,
            last_name = follwer.last_name,
            email = follwer.email
        )
    }
}


