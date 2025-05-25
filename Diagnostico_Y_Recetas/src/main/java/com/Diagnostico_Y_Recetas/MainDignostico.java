package com.Diagnostico_Y_Recetas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication 
public class MainDignostico {
    public static void main(String[] args) {
        SpringApplication.run(MainDignostico.class, args);
        System.out.println("\n  ¡Bienvenido a Diagnóstico y Recetas!   \n");
    }
}