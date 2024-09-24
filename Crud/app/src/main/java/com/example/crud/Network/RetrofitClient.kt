package com.example.crud.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitClient {

    private const val BASE_URL = "http://192.168.100.36:8081" // Dirección IP de tu backend

    // Configuración del interceptor para el logging
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Cliente OkHttp con el interceptor de logging
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    // Instancia de Retrofit
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    // API Service creado a partir de la interfaz ApiService
    val apiService: ApiService = retrofit.create(ApiService::class.java)
}