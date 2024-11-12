import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaAPI {
    public Double buscaDivisa(String divisa1, String divisa2) {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/10d34266a00a554f12e3bd07/latest/" + divisa1);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");
            return conversionRates.get(divisa2).getAsDouble();

        } catch (Exception e) {
            throw new RuntimeException("No encontré la divisa");
        }
    }
}
