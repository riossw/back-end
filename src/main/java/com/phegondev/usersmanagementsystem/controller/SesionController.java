package com.phegondev.usersmanagementsystem.controller;

import com.phegondev.usersmanagementsystem.dto.SesionDTO;
import com.phegondev.usersmanagementsystem.entity.Sesion;
import com.phegondev.usersmanagementsystem.service.SesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sesiones")
public class SesionController {

    @Autowired
    private SesionService sesionService;

    // Crear una nueva sesión
    @PostMapping("/create")
    public ResponseEntity<Sesion> createSesion(@RequestBody Sesion sesion) {
        Sesion newSesion = sesionService.createSesion(
                sesion.getLink(),
                sesion.getNombre(),
                sesion.getOurUser().getId() // Aquí obtenemos el ID del objeto `OurUser` anidado
        );
        return ResponseEntity.ok(newSesion);
    }

    // Obtener todas las sesiones
    @GetMapping("/all")
    public ResponseEntity<List<SesionDTO>> getAllSesiones() {
        List<SesionDTO> sesiones = sesionService.getAllSesiones();
        return ResponseEntity.ok(sesiones);
    }


    // Obtener sesiones por ID de usuario
    @GetMapping("/all/{userId}")
    public ResponseEntity<List<SesionDTO>> getSesionesByUserId(@PathVariable Integer userId) {
        List<SesionDTO> sesiones = sesionService.getSesionesByUserId(userId)
                .stream()
                .map(sesion -> new SesionDTO(
                        sesion.getId(),
                        sesion.getLink(),
                        sesion.getNombre(),
                        sesion.getOurUser().getId() // Devuelve el ID del usuario en lugar de toda la entidad
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(sesiones);
    }

    // Eliminar una sesión por su ID
    // Eliminar una sesión por su ID
    @DeleteMapping("/delete/{sesionId}")
    public ResponseEntity<String> deleteSesion(@PathVariable Integer sesionId) {
        boolean deleted = sesionService.deleteSesion(sesionId);
        if (deleted) {
            return ResponseEntity.ok("Sesión eliminada exitosamente.");
        } else {
            return ResponseEntity.status(404).body("Sesión no encontrada.");
        }
    }

}
