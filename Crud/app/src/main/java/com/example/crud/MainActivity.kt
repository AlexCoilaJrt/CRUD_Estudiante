package com.example.crud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.crud.Model.Student
import com.example.crud.Network.RetrofitClient
import com.example.crud.ui.theme.Screen.StudentScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                StudentScreen()
            }
        }
    }
}

@Composable
fun StudentScreen() {
    var students by remember { mutableStateOf(listOf<Student>()) }

    // Llamada a la API
    LaunchedEffect(Unit) {
        fetchStudents { fetchedStudents ->
            students = fetchedStudents
        }
    }

    // Mostrar los estudiantes en una LazyColumn
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(students) { student ->
            StudentItem(student = student)
        }
    }
}

@Composable
fun StudentItem(student: Student) {
    Text(
        text = "${student.nombre} - ${student.grado}",
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

suspend fun fetchStudents(onResult: (List<Student>) -> Unit) {
    withContext(Dispatchers.IO) {
        try {
            // Llamada directa a la función suspendida de Retrofit
            val studentsList = RetrofitClient.apiService.getStudents()

            // Si la llamada es exitosa, retorna la lista de estudiantes
            onResult(studentsList)
        } catch (e: Exception) {
            e.printStackTrace()
            // En caso de error, retorna una lista vacía
            onResult(emptyList())
        }
    }
}
