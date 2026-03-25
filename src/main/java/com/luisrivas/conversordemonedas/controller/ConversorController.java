package com.luisrivas.conversordemonedas.controller;

import com.luisrivas.conversordemonedas.service.ConsultaAPI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ConversorController {

    private final ConsultaAPI consultaAPI;

    // Inyección de dependencia
    public ConversorController(ConsultaAPI consultaAPI) {
        this.consultaAPI = consultaAPI;
    }

    @GetMapping("/convertir")
    public Map<String, Object> convertir(
            @RequestParam String base,
            @RequestParam String destino) {

        double resultado = consultaAPI.buscaDivisa(base, destino);

        return Map.of(
                "base", base,
                "destino", destino,
                "resultado", resultado
        );
    }
}
