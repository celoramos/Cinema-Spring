package br.com.cinema.principal;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import br.com.cinema.model.Serie;
import br.com.cinema.model.DadosSerie;
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
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida");
                continue;
            }

            switch (opcao) {
                case 1:
                    DadosSerie serieBuscada = buscarSerieWeb();
                    if (serieBuscada == null) {
                        break;
                    }
                    System.out.println("Deseja buscar episódios dessa série? (Sim/Não)");
                    String resposta = scanner.nextLine();
                    if (resposta.equalsIgnoreCase("Sim")) {
                        buscarEpisodioPorSerie(serieBuscada);
                    }
                    break;
                case 2: buscarFilmeWeb();
                    break;
                case 3: listarSeriesBuscadas();
                    break;
                case 0: System.out.println("Saindo...");
                    break;
                default: System.out.println("Opção inválida");
            }
        }
    }

    private DadosSerie buscarSerieWeb() {
        System.out.println("Digite o nome da série para busca");
        String nomeSerie = scanner.nextLine().trim();
        if (nomeSerie.isEmpty()) {
            System.out.println("O nome da série não pode ficar vazio.");
            return null;
        }

        DadosSerie dados = getDadosSerie(nomeSerie);
        if (dados == null || dados.Title() == null) {
            System.out.println("Série não encontrada ou não foi possível consultar a API.");
            return null;
        }
        dadosSeries.add(dados);
        System.out.println(dados);
        return dados;
    }

    private DadosSerie getDadosSerie(String nomeSerie) {
        var json = consumoAPI.ObterDados(ENDERECOURL + codificarParametro(nomeSerie) + APIKEY);
        return converterDados.obterDados(json, DadosSerie.class);
    }

    private void buscarEpisodioPorSerie(DadosSerie dadosSerie) {
        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            var json = consumoAPI.ObterDados(ENDERECOURL + codificarParametro(dadosSerie.Title()) + "&season=" + i + APIKEY);
            DadosTemporada dadosTemporada = converterDados.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
    }

    private void buscarFilmeWeb() {
        System.out.println("Digite o nome do filme para busca:");
        var nomeFilme = scanner.nextLine().trim();
        if (nomeFilme.isEmpty()) {
            System.out.println("O nome do filme não pode ficar vazio.");
            return;
        }
        var json = consumoAPI.ObterDados(ENDERECOURL + codificarParametro(nomeFilme) + "&type=movie" + APIKEY);
        DadosSerie dados = converterDados.obterDados(json, DadosSerie.class);
        if (dados == null || dados.Title() == null) {
            System.out.println("Filme não encontrado ou não foi possível consultar a API.");
            return;
        }
        System.out.println(dados);
    }

    private String codificarParametro(String valor) {
        return URLEncoder.encode(valor, StandardCharsets.UTF_8);
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
