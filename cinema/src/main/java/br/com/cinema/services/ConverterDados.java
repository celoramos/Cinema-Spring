package br.com.cinema.services;

import br.com.cinema.model.DadosSerie;
import tools.jackson.databind.ObjectMapper;

public class ConverterDados implements IConverteDados {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe); // transforma em objeto Java
        } catch (Exception e) {
            System.out.println("Erro ao converter os dados: " + e.getMessage());
            return null;
        }
    }

    @Override
    public DadosSerie converterDados(String json) {
        return null;
    }
}
