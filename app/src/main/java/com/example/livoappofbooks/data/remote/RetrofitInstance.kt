package com.example.livoappofbooks.data.remote

import android.content.Context
import com.example.livoappofbooks.data.remote.local.TokenManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {

    private var api: AuthService? = null

    fun getApi(context: Context): AuthService {
        if (api == null) {
            val tokenManager = TokenManager(context.applicationContext)

            val authInterceptor = Interceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                tokenManager.getToken()?.let { token ->
                    requestBuilder.addHeader("Authorization", "Bearer $token")
                }
                chain.proceed(requestBuilder.build())
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .build()

            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://172.25.171.99:8080/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(client)
                .build()

            api = retrofit.create(AuthService::class.java)
        }
        return api!!
    }
}