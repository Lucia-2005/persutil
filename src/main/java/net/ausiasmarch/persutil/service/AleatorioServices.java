package net.ausiasmarch.persutil.service;

import org.springframework.stereotype.Service;

@Service
public class AleatorioServices {
    public int GenerarNumeroAleatorioEnteroEnRango(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
