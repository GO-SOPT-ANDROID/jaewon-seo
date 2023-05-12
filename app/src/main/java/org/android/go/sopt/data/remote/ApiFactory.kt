package org.android.go.sopt.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.android.go.sopt.BuildConfig
import org.android.go.sopt.data.remote.service.FollowerService
import org.android.go.sopt.data.remote.service.SignInService
import org.android.go.sopt.data.remote.service.SignUpService
import retrofit2.Retrofit

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
            .baseUrl("https://reqres.in")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object ServicePool {
    val signUpService = SignInUpApiFactory.create<SignUpService>()
    val signInService = SignInUpApiFactory.create<SignInService>()
    val followerService = ReqresApiFactory.create<FollowerService>()
}