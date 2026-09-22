package br.com.cinema.principal;

import br.com.cinema.model.DadosEpisodio;
import br.com.cinema.model.DadosTemporada;
import br.com.cinema.model.Episodio;
import br.com.cinema.services.ConsumoAPI;
import br.com.cinema.services.ConverterDados;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.StableValue.map;

public class Principal {
     Scanner scanner = new Scanner(System.in);
     private ConsumoAPI consumoAPI = new ConsumoAPI();
     private ConverterDados converterDados = new ConverterDados();

     private final String ENDERECOURL = "http://www.omdbapi.com/?t=";
     private final String APIKEY = "&apikey=fa505d00";

    public void exibirMenu() {
        System.out.println("Bem-vindo ao Cinema!");
        System.out.println("Digite o nome da série ou filme que você deseja buscar:");
        var pesquisaSerieFilme = scanner.nextLine();

        System.out.println("Procurando por " + pesquisaSerieFilme + "...");
        String urlConsulta = ENDERECOURL + pesquisaSerieFilme.replace(" ", "+") + APIKEY;

        List<DadosTemporada> temporadas = new ArrayList<>();
        for (int i = 1; ; i++) {
            String json = consumoAPI.ObterDados(ENDERECOURL + pesquisaSerieFilme.replace(" ", "+") + "&season=" + i + APIKEY);
            DadosTemporada temporada = converterDados.obterDados(json, DadosTemporada.class);
            if (temporada == null || temporada.episodios() == null || temporada.episodios().isEmpty()) break;
            temporadas.add(temporada);
        }
            if (temporadas.isEmpty()) {
            System.out.println("Nenhum episódio encontrado para a temporada " + temporadas.size());
            } else {
                System.out.println("LISTA DE TODOS OS EPISÓDIOS DA SÉRIE: " + pesquisaSerieFilme.toUpperCase());
                temporadas.stream()
                        .flatMap(t -> t.episodios().stream());
                temporadas.stream()
                        .flatMap(t -> t.episodios().stream())
                        .forEach(episodio -> System.out.println("Episódio " + episodio.numeroEps()
                                + " - " + episodio.Title()
                                + " - " + episodio.avaliacaoImdb()));
            }
            List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                    .flatMap(t -> t.episodios().stream())
                    .collect(Collectors.toList());
            System.out.println("\nTOP 5 EPISÓDIOS COM MELHOR AVALIAÇÃO NO IMDB:");
            dadosEpisodios.stream()
                    .filter(e -> !e.avaliacaoImdb().equals("N/A"))
                    .sorted(Comparator.comparing(DadosEpisodio::avaliacaoImdb).reversed())
                    .limit(5)
                    .forEach(System.out::println);

            List<Episodio> episodios = temporadas.stream()
                    .flatMap(temporada -> temporada.episodios().stream())
                    .map(dadosEpisodios -> new Episodio(temporadas.numero(),
                            dadosEpisodios.numeroEps(), dadosEpisodios.Title(),
                            Double.parseDouble(dadosEpisodios.avaliacaoImdb())))
                    .collect(Collectors.toList());
    }
}
