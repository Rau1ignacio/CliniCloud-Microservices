package com.Usuarios.Usuarios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity  // Marca esta clase como una entidad JPA.
@Table(name= "medico")  // Especifica el nombre de la tabla en la base de datos.
@Data  // Genera automáticamente getters, setters, equals, hashCode y toString.
@NoArgsConstructor  // Genera un constructor sin argumentos.
@AllArgsConstructor  // Genera un constructor con un argumento por cada campo en la clase.

public class Medico {
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Integer id;

    @Column(unique=true, length = 13, nullable=false)  
    private String run;

    @Column(nullable=false)  
    private String nombres;

    @Column(nullable=false) 
    private String apellidos;

    @Column(nullable=true) 
    private Date fechaNacimiento;

    @Column(nullable=false)  
    private String correo;

    @Column(unique=true, length = 9, nullable=false) 
    private String fono;
}

