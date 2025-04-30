package com.phegondev.usersmanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SesionDTO {
    private Integer id;
    private String link;
    private String nombre;
    private Integer userId; // Solo devuelve el ID del usuario, no toda la entidad del usuario.

    public SesionDTO(Integer id, String link, String nombre, Integer userId) {
        this.id = id;
        this.link = link;
        this.nombre = nombre;
        this.userId = userId;
    }

    // Getters y Setters
}

