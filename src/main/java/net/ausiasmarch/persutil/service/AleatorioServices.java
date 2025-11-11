package net.ausiasmarch.persutil.service;

import org.springframework.stereotype.Service;

@Service
public class AleatorioServices {
    public int GenerarNumeroAleatorioEnteroEnRango(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public String[] generarFraseAleatoria() {
        String[] palabras = { 
            "Hola", "mundo", "esto", "es", "una", "frase", "aleatoria", 
            "generada", "por", "servicio", "soy", "lucia", "proyecto", 
            "folklore", "taylor", "java", "spring", "api", "rest", "json",
            "generar", "frase", "aleatoria", "nube", "software"
        };
        String[] frase = new String[5];
        for (int i = 0; i < 5; i++) {
            int indiceAleatorio = (int) (Math.random() * palabras.length);
            frase[i] = palabras[indiceAleatorio];
        }
        return frase;
    }

}
