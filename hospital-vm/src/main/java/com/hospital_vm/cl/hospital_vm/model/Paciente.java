
package com.hospital_vm.cl.hospital_vm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity  // Marca esta clase como una entidad JPA.
@Table(name= "paciente")  // Especifica el nombre de la tabla en la base de datos.
@Data  // Genera automáticamente getters, setters, equals, hashCode y toString.
@NoArgsConstructor  // Genera un constructor sin argumentos.
@AllArgsConstructor  // Genera un constructor con un argumento por cada campo en la clase.

public class Paciente {

    @Id  // Especifica el identificador primario.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // El valor del ID se generará automáticamente.
    private int id;

    @Column(unique = true, length = 10, nullable = false)  // Define las restricciones para la columna en la tabla.
    private String run;

    @Column(nullable=false)  // Esta columna no puede ser nula.
    private String nombres;

    @Column(nullable=false)  // Esta columna no puede ser nula.
    private String apellidos;

    @Column(nullable=true)  // Esta columna puede ser nula.
    private Date fechaNacimiento;

    @Column(nullable=false)  // Esta columna no puede ser nula.
    private String correo;
}
