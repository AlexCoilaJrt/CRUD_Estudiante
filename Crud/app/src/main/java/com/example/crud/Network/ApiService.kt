package com.example.crud.Network
import retrofit2.http.GET
import com.example.crud.Model.Student

interface ApiService {
    @GET("/students")
    suspend fun getStudents(): List<Student> // Función suspendida
}