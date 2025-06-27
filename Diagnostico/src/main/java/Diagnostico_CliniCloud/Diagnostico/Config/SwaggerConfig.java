package Diagnostico_CliniCloud.Diagnostico.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean

    /*
     * Configuración de Swagger para la API de Diagnóstico
     * Este método define la información básica de la API, como el título, la versión y la descripción.
     */

    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()// Creo la info para la API
                        .title("CliniCloud Diagnostico API")
                        .version("1.0")
                        .description("Documentacion de la API para gestionar diagnósticos en CliniCloud"));
    }

}
