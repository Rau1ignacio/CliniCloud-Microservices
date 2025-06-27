package Diagnostico_CliniCloud.Diagnostico.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class AppConfig {

    @Bean

    /** 
     *  Bean para RestTemplate, utilizado para realizar 
     *  llamadas HTTP a servicios externos, como el servicio de Citas.
    */
    
    public RestTemplate restTemplate() { // Método que crea y devuelve una instancia de RestTemplate
        return new RestTemplate();
    }

}
