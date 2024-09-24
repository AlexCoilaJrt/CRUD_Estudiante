package org.example.msestudiante.Service.Impl;

import org.example.msestudiante.Entity.Estudiante;
import org.example.msestudiante.Repository.EstudianteRepository;
import org.example.msestudiante.Service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteServiceImpl implements EstudianteService {
    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public List<Estudiante> listar() {
        return estudianteRepository.findAll();
    }

    @Override
    public Estudiante guardar(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public Estudiante buscarPorId(Integer id) {
        return estudianteRepository.findById(id).orElse(null); // Return null if not found
    }

    @Override
    public Estudiante editar(Integer id, Estudiante estudiante) {
        estudiante.setId(id); // Set the ID for the existing entity
        return estudianteRepository.save(estudiante);
    }

    @Override
    public void eliminar(Integer id) {
        estudianteRepository.deleteById(id);
    }
}