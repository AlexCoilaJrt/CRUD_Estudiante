package com.example.crud.ui.theme

import androidx.lifecycle.ViewModel
import com.example.crud.Model.Student
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class EstudianteViewModel : ViewModel() {

    private val _estudiantes = MutableStateFlow<List<Student>>(emptyList())
    val estudiantes: StateFlow<List<Student>> = _estudiantes

    private var currentId = 1

    // Crear estudiante
    fun createEstudiante(estudiante: Student) {
        val newEstudiante = estudiante.copy(id = currentId++)
        _estudiantes.value = _estudiantes.value + newEstudiante
    }

    // Eliminar estudiante
    fun deleteEstudiante(id: Int) {
        _estudiantes.value = _estudiantes.value.filter { it.id != id }
    }
}