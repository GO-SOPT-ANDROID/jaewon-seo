package org.android.go.sopt.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.android.go.sopt.BuildConfig
import org.android.go.sopt.data.remote.interceptor.AuthInterceptor
import org.android.go.sopt.data.remote.service.*
import retrofit2.Retrofit
import retrofit2.create

object ApiFactory {
    private val client by lazy {
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }).addInterceptor(AuthInterceptor()).build()
    }

    private const val BASE_URL = BuildConfig.AUTH_BASE_URL //local properties에 base url 기재
    val retrofitforImage: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofitforImage.create<T>(T::class.java)


}

object SignInUpApiFactory {
    private const val BASE_URL = BuildConfig.AUTH_BASE_URL //local properties에 base url 기재
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object ReqresApiFactory {
    private const val BASE_URL = BuildConfig.REQRES_BASE_URL //local properties에 base url 기재
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object MusicApiFactory {
    private const val BASE_URL = BuildConfig.AUTH_BASE_URL //local properties에 base url 기재
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object ServicePool {
    val signUpService = SignInUpApiFactory.create<SignUpService>()
    val signInService = SignInUpApiFactory.create<SignInService>()
    val followerService = ReqresApiFactory.create<FollowerService>()
    val imageService = ApiFactory.retrofitforImage.create<ImageService>()
    val getMusicService = MusicApiFactory.create<GetMusicService>()
    val postMusicService = MusicApiFactory.create<PostMusicService>()
}