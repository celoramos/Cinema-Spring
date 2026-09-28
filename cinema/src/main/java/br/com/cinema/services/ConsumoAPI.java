package br.com.cinema.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ConsumoAPI {
    // um único client reaproveitado entre as chamadas, com tempo máximo para conectar
    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public String ObterDados(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(60)) // sem isso a requisição pode esperar para sempre
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body(); // corpo da resposta
        } catch (IOException e) {
            System.out.println("Erro ao consumir a API: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Erro ao consumir a API: " + e.getMessage());
        }
        return null;
    }
}
