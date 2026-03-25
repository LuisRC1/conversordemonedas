package com.luisrivas.conversordemonedas.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class ConsultaAPI {

    public double buscaDivisa(String divisaBase, String divisaDestino) {

        // Validación de entradas
        if (divisaBase == null || divisaBase.isBlank() ||
                divisaDestino == null || divisaDestino.isBlank()) {
            throw new IllegalArgumentException("Las divisas no pueden ser nulas o vacías");
        }

        // API KEY
        String apiKey = System.getenv("EXCHANGE_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("No se encontró la variable de entorno EXCHANGE_API_KEY");
        }

        // URL
        URI direccion = URI.create(
                "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + divisaBase
        );

        // Cliente HTTP
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        // Request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .timeout(Duration.ofSeconds(10))
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("Error HTTP: " + response.statusCode());
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(response.body());

            // Validar respuesta API
            if (!"success".equalsIgnoreCase(json.get("result").asText())) {
                throw new RuntimeException("La API devolvió un resultado no exitoso");
            }

            // Obtener conversion_rates
            JsonNode conversionRates = json.get("conversion_rates");

            if (conversionRates == null ||
                    conversionRates.get(divisaDestino.toUpperCase()) == null) {
                throw new RuntimeException("No se encontró la tasa para " + divisaDestino);
            }

            // Retornar valor
            return conversionRates.get(divisaDestino.toUpperCase()).asDouble();

        } catch (Exception e) {
            throw new RuntimeException("Error al consultar la API: " + e.getMessage(), e);
        }
    }
}

//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.time.Duration;
//
//public class ConsultaAPI {
//    public double buscaDivisa(String divisaBase, String divisaDestino) {
//        // Validación de entradas
//        if (divisaBase == null || divisaBase.isBlank() || divisaDestino == null || divisaDestino.isBlank()){
//            throw new IllegalArgumentException("Las divisas no pueden ser nulas o vacías");
//        }
//
//        // Obtener la API key de una variable de entorno
//        String apiKey = System.getenv("EXCHANGE_API_KEY");
//        if (apiKey == null || apiKey.isBlank()) {
//            throw new IllegalStateException("No se encontró la variable de entorno EXCHANGE_API_KEY");
//        }
//
//        // Construir la URL con la divisa base
//        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + divisaBase);
//
//        // Crear el cliente HTTP con timeout
//        HttpClient client = HttpClient.newBuilder()
//                .connectTimeout(Duration.ofSeconds(10))
//                .build();
////        HttpClient client = HttpClient.newHttpClient();
//
//        // Crear la solicitud con timeout por petición
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(direccion)
//                .timeout(Duration.ofSeconds(10))
//                .build();
//
//        try {
//            // Enviar la solicitud y recibir la respuesta como texto
//            HttpResponse<String> response = client
//                    .send(request, HttpResponse.BodyHandlers.ofString());
//
//            // Comprobar código de estado HTTP
//            if (response.statusCode() != 200) {
//                throw new RuntimeException("Error en la solicitud: código HTTP " + response.statusCode());
//            }
//            //Transformar el JSON de la respuesta
//            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
//
//            // Verificar que la API respondió correctamente
//            if (!"success".equalsIgnoreCase(jsonObject.get("result").getAsString())) {
//                throw new RuntimeException("La API devolvió un resultado no exitoso");
//            }
//
//            // Obtener el objeto de tasas de conversión
//            JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");
//
//            if (conversionRates == null || !conversionRates.has(divisaDestino.toUpperCase())) {
//                throw new RuntimeException("No se encontró la tasa de conversión para " + divisaDestino);
//            }
//
//            // Devolver la tasa de conversión como Double
//            return conversionRates.get(divisaDestino).getAsDouble();
//
//        } catch (Exception e) {
//            throw new RuntimeException("Error al consultar la API de divisas: " + e.getMessage(), e);
//        }
//    }
//}
