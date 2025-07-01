package com.AgendarCitasMedicas.AgendarCitasMedicas;

import com.AgendarCitasMedicas.AgendarCitasMedicas.model.CitasMedica;
import com.AgendarCitasMedicas.AgendarCitasMedicas.repository.CitasRepository;
import jakarta.transaction.Transactional;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Random;


@Component
public class Datafaker implements CommandLineRunner {

    private final Random random = new Random();

    @Autowired
    private CitasRepository citasRepository;
    private final Faker faker = new Faker(new Locale("es"));

    @Transactional
    @Override
    public void run(String ...args)throws Exception{
        Faker faker = new Faker();


        //Para crear una cita medica
        for (int i = 0; i < 10; i++) {
            CitasMedica cita = new CitasMedica();
            cita.setRut(generarRut());
            cita.setNombre_paciente(faker.name().fullName());
            cita.setApellido_paciente(faker.name().lastName());
            cita.setCorreo_paciente(faker.internet().emailAddress());
            cita.setTelefono(faker.phoneNumber().cellPhone());
            cita.setNombre_medico("Dr. " + faker.name().lastName());
            cita.setEspecialidad_medico(getEspecialidad());
            cita.setFecha_hora_cita(generarFechaHora());
            cita.setDescripcion(faker.lorem().sentence(10));

            citasRepository.save(cita);
        }
    }

    private String generarRut() {  // ← Sin parámetros
        int cuerpo = 10_000_000 + random.nextInt(15_000_000);
        char dv = (char) ('0' + (cuerpo % 10));
        return cuerpo + "-" + dv;
    }
    private String getEspecialidad() {
        String[] especialidades = {"Cardiología", "Pediatría", "Neurología", "Traumatología", "Ginecología"};
        Random random = new Random();
        return especialidades[random.nextInt(especialidades.length)];
    }

    private LocalDateTime generarFechaHora(){
        Random random = new Random();
        return LocalDateTime.now()
                .plusDays(random.nextInt(30))
                .withHour(8 + random.nextInt(9))  // entre 8 y 16 hrs
                .withMinute(random.nextBoolean() ? 0 : 30);
    }

}




