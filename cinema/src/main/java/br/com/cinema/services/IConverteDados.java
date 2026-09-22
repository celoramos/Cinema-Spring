package br.com.cinema.services;

import br.com.cinema.model.DadosSerie;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
    DadosSerie converterDados(String json);
}
