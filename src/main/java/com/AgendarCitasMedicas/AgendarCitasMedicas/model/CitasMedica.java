package com.AgendarCitasMedicas.AgendarCitasMedicas.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "Cita")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitasMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cita;

    @Column(unique = true,length = 12, nullable = false)
    private String rut;

    @Column(nullable = false)
    private String nombre_paciente;

    @Column(nullable = true)
    private String apellido_paciente;

    @Column(length = 255,nullable = false)
    private String correo_paciente;

    @Column(length = 15, nullable = false)
    private String telefono;

    @Column(nullable= false)
    private String nombre_medico;

    @Column(nullable = false)
    private String  especialidad_medico;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime fecha_hora_cita;

    @Column(length = 255, nullable = false)
    private String descripcion;

}
