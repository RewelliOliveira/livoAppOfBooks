package com.example.livoappofbooks.data.remote

import android.content.Context
import com.example.livoappofbooks.data.remote.local.TokenManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {

    //bota teu ip aq
    private const val BASE_URL = "http://192.168.7.101:8080/"

    fun <T> createService(context: Context,
        serviceClass: Class<T>
    ): T {

        val tokenManager = TokenManager(context.applicationContext)

        val authInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val path = originalRequest.url.encodedPath

            if (path.contains("/auth") || path.contains("/user")) {
                return@Interceptor chain.proceed(originalRequest)
            }

            val token = tokenManager.getToken()

            val newRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()

            chain.proceed(newRequest)
        }

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(serviceClass)
    }
}
