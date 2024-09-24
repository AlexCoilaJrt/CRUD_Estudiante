package org.example.msestudiante.Controller;



import org.example.msestudiante.Entity.Estudiante;
import org.example.msestudiante.Service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {
    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<List<Estudiante>> listar() {
        return ResponseEntity.ok(estudianteService.listar());
    }

    @PostMapping
    public ResponseEntity<Estudiante> guardar(@RequestBody Estudiante estudiante) {
        return ResponseEntity.ok(estudianteService.guardar(estudiante));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> buscarPorId(@PathVariable(required = true) Integer id) {
        return ResponseEntity.ok(estudianteService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> editar(@PathVariable(required = true) Integer id, @RequestBody Estudiante estudiante) {
        return ResponseEntity.ok(estudianteService.editar(id, estudiante));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable(required = true) Integer id) {
        estudianteService.eliminar(id);
        return ResponseEntity.ok("Eliminación correcta");
    }
}
