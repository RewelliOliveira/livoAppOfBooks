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

    private var api: AuthService? = null

    // !! IMPORTANTE !!
    // Troque "SEU_IP_AQUI" pelo endereço de IP do seu computador na rede Wi-Fi.
    private const val BASE_URL = "http://172.25.171.99:8080/"

    fun getApi(context: Context): AuthService {
        if (api == null) {
            val tokenManager = TokenManager(context.applicationContext)

            // Interceptor para adicionar o token de autenticação
            val authInterceptor = Interceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                tokenManager.getToken()?.let { token ->
                    requestBuilder.addHeader("Authorization", "Bearer $token")
                }
                chain.proceed(requestBuilder.build())
            }

            // Interceptor para logar as requisições (APENAS PARA DEBUG)
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .addInterceptor(loggingInterceptor) // Adiciona o interceptor de log
                .build()

            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(client)
                .build()

            api = retrofit.create(AuthService::class.java)
        }
        return api!!
    }
}
