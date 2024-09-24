package org.example.msestudiante.Service;
import org.example.msestudiante.Entity.Estudiante;

import java.util.List;

public interface EstudianteService {
    List<Estudiante> listar();
    Estudiante guardar(Estudiante estudiante);
    Estudiante buscarPorId(Integer id);
    Estudiante editar(Integer id, Estudiante estudiante);
    void eliminar(Integer id);
}