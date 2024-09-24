package com.example.crud.ui.theme.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crud.Model.Student
import com.example.crud.ui.theme.EstudianteViewModel

@Composable
fun StudentScreen(viewModel: EstudianteViewModel = viewModel()) {
    val estudiantes by viewModel.estudiantes.collectAsState()

    var nombre by remember { mutableStateOf("") }
    var grado by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Formulario para agregar estudiante
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        TextField(
            value = grado,
            onValueChange = { grado = it },
            label = { Text("Grado") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Button(
            onClick = {
                val estudiante = Student(0, nombre, grado)
                viewModel.createEstudiante(estudiante)
                nombre = ""  // Limpiar el campo después de agregar
                grado = ""   // Limpiar el campo después de agregar
            },
            enabled = nombre.isNotBlank() && grado.isNotBlank()
        ) {
            Text("Agregar Estudiante")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de estudiantes
        LazyColumn {
            items(estudiantes) { estudiante ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(text = estudiante.nombre, Modifier.weight(1f))
                    Text(text = estudiante.grado, Modifier.weight(1f))
                    Button(onClick = { viewModel.deleteEstudiante(estudiante.id) }) {
                        Text("Eliminar")
                    }
                }
            }
        }
    }
}