package org.android.go.sopt.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.android.go.sopt.data.remote.ServicePool.postMusicService
import org.android.go.sopt.data.remote.dto.ResponsePostMusicDto
import org.android.go.sopt.util.ContentUriRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GalleryViewModel : ViewModel() {
    private val _image = MutableLiveData<ContentUriRequestBody>()
    val image: LiveData<ContentUriRequestBody>
        get() = _image

    fun setRequestBody(requestBody: ContentUriRequestBody) {
        _image.value = requestBody
        Log.d("ImageSet", "Image has been set.")
    }

    fun postMusicData(id: String, _title: String, _singer: String) {
        if (image.value == null) { /* 아직 사진이 등록되지 않았다는 로직 처리 */
        } else {
            val title = _title.toRequestBody("text/plain".toMediaTypeOrNull())
            val singer = _singer.toRequestBody("text/plain".toMediaTypeOrNull())

            postMusicService.postMusic(id, image.value!!.toFormData(), title, singer).enqueue(
                object : Callback<ResponsePostMusicDto> {
                    override fun onResponse(
                        call: Call<ResponsePostMusicDto>,
                        response: Response<ResponsePostMusicDto>
                    ) {
                        if (response.isSuccessful)
                            Log.d("GOOD?", "success !!!")
                        else {
                            Log.d("GOOD?", "Response Code: ${response.code()}")
                            Log.d("GOOD?", "Response Message: ${response.message()}")
                            Log.d("GOOD?", "Response Headers: ${response.headers()}")
                            Log.d("GOOD?", "Response Body: ${response.errorBody()?.string()}")
                        }
                    }

                    override fun onFailure(call: Call<ResponsePostMusicDto>, t: Throwable) {
                        Log.e("API Error", t.message, t)
                    }
                }
            )
        }
    }
}
