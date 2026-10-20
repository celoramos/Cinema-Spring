package br.com.cinema.principal;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.Collectors;

import br.com.cinema.model.DadosSerie;
import br.com.cinema.model.Serie;
import br.com.cinema.services.ConsumoAPI;
import br.com.cinema.model.DadosTemporada;
import br.com.cinema.services.ConverterDados;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverterDados converterDados = new ConverterDados();
    private final String ENDERECOURL = "https://www.omdbapi.com/?t=";
    private final String APIKEY = "&apikey=fa505d00";
    private List<DadosSerie> dadosSeries = new ArrayList<>();

    public void exibirMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1 - Buscar séries
                    2 - Buscar filmes
                    3 - Listar séries buscadas
                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    DadosSerie serieBuscada = buscarSerieWeb();
                    System.out.println("Deseja buscar episódios dessa série? (Sim/Não)");
                    String resposta = scanner.nextLine();
                    if (resposta.equalsIgnoreCase("Sim")) {
                        buscarEpisodioPorSerie(serieBuscada);
                    }
                    break;
                case 2: buscarFilmeWeb();
                    break;
                case 3: listarSeriesBuscadas();;
                    break;
                case 0: System.out.println("Saindo...");
                    break;
                default: System.out.println("Opção inválida");
            }
        }
    }

    private DadosSerie buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
        dadosSeries.add(dados);
        System.out.println(dados);
        return dados;
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = scanner.nextLine();
        var json = consumoAPI.ObterDados(ENDERECOURL + nomeSerie.replace(" ", "+") + APIKEY);
        DadosSerie dados = converterDados.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie(DadosSerie dadosSerie) {
        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            var json = consumoAPI.ObterDados(ENDERECOURL + dadosSerie.Title().replace(" ", "+") + "&season=" + i + APIKEY);
            DadosTemporada dadosTemporada = converterDados.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
    }

    private void buscarFilmeWeb() {
        System.out.println("Digite o nome do filme para busca:");
        var nomeFilme = scanner.nextLine();
        var json = consumoAPI.ObterDados(ENDERECOURL + nomeFilme.replace(" ", "+") + "&type=movie" + APIKEY);
        DadosSerie dados = converterDados.obterDados(json, DadosSerie.class);
        System.out.println(dados);
    }

    private void listarSeriesBuscadas() {
        List<Serie> series = new ArrayList<>();
        series = dadosSeries.stream()
                .map(Serie::new)
                .collect(Collectors.toList());
        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }
    }
