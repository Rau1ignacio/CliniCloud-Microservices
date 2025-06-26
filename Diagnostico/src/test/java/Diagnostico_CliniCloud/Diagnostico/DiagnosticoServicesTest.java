package Diagnostico_CliniCloud.Diagnostico;

import Diagnostico_CliniCloud.Diagnostico.Model.Diagnostico;
import Diagnostico_CliniCloud.Diagnostico.Repository.DiagnosticoRepository;
import Diagnostico_CliniCloud.Diagnostico.Services.DiagnosticoServices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals; // Importamos las aserciones de JUnit para verificar resultados
import static org.mockito.ArgumentMatchers.any;              // Importamos cualquier para simular el comportamiento del repositorio
import static org.mockito.Mockito.when;                      // Importamos Mockito para simular el comportamiento del repositorio

@SpringBootTest
public class DiagnosticoServicesTest {
    @Autowired
    private DiagnosticoServices diagnosticoServices;

    @MockBean
    private DiagnosticoRepository diagnosticoRepository; // 

    @Test
    public void testGuardarDiagnostico() { 
            // Esto es un test para guardar un diagnóstico
            // Creamos un diagnóstico de prueba
            // y configuramos el mock del repositorio para que retorne este diagnóstico
            // cuando se llame al método save
        Diagnostico diagnostico = new Diagnostico(); // Creamos una instancia de Diagnostico
        diagnostico.setNombre("Juan Perez"); // Establecemos el nombre del diagnóstico
        when(diagnosticoRepository.save(any(Diagnostico.class))).thenReturn(diagnostico); // Esto configura el mock para que retorne el diagnóstico cuando se llame al método save

        Diagnostico resultado = diagnosticoServices.save(diagnostico); // Llamamos al método save del servicio, que a su vez llama al repositorio mockeado

        assertEquals("Juan Perez", resultado.getNombre()); // Verificamos que el nombre del diagnóstico guardado sea el mismo que el del diagnóstico de prueba
        // Esto asegura que el método save del servicio está funcionando correctamente
    }
}
