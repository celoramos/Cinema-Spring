package br.com.cinema.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    public String ObterDados(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = null; // resposta da requisição
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            System.out.println("Erro ao consumir a API: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Erro ao consumir a API: " + e.getMessage());
        }
        String json = response.body(); // corpo da resposta
        return json;
    }
}

