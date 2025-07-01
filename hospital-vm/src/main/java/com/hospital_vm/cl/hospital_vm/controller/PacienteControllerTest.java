package com.hospital_vm.cl.hospital_vm.controller;

import com.hospital_vm.cl.hospital_vm.model.Paciente;
import com.hospital_vm.cl.hospital_vm.service.PacienteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PacienteController.class)
public class PacienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PacienteService pacienteService;

    @Test
    public void testCrearPaciente() throws Exception {
        Paciente paciente = new Paciente(1L, "12345678-9", "Carlos", "Martínez", "2000-01-01", "carlos.martinez@example.com");

        Mockito.when(pacienteService.save(Mockito.any(Paciente.class))).thenReturn(paciente);

        String pacienteJson = """
            {
                "run": "12345678-9",
                "nombres": "Carlos",
                "apellidos": "Martínez",
                "fechaNacimiento": "2000-01-01",
                "correo": "carlos.martinez@example.com"
            }
        """;

        mockMvc.perform(post("/api/v1/pacientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pacienteJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombres").value("Carlos"))
                .andExpect(jsonPath("$.apellidos").value("Martínez"));
    }

    @Test
    public void testBuscarPacientePorId() throws Exception {
        Paciente paciente = new Paciente(1L, "12345678-9", "Carlos", "Martínez", "2000-01-01", "carlos.martinez@example.com");

        Mockito.when(pacienteService.findById(1L)).thenReturn(Optional.of(paciente));

        mockMvc.perform(get("/api/v1/pacientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombres").value("Carlos"))
                .andExpect(jsonPath("$.apellidos").value("Martínez"));
    }

    @Test
    public void testEliminarPaciente() throws Exception {
        Mockito.doNothing().when(pacienteService).delete(1L);

        mockMvc.perform(delete("/api/v1/pacientes/1"))
                .andExpect(status().isNoContent());
    }
}
