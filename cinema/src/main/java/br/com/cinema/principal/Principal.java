package br.com.cinema.principal;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
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
                    2 - Buscar episódios
                    3 - Buscar filmes
                    4 - Listar séries buscadas

                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    buscarFilmeWeb();
                    break;
                case 4:
                    listarSeriesBuscadas();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
        dadosSeries.add(dados);
        System.out.println(dados);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = scanner.nextLine();
        var json = consumoAPI.ObterDados(ENDERECOURL + nomeSerie.replace(" ", "+") + APIKEY);
        DadosSerie dados = converterDados.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie() {
        DadosSerie dadosSerie = getDadosSerie();
        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            var json = consumoAPI.ObterDados(ENDERECOURL + dadosSerie.Title().replace(" ", "+") + "&season=" + i + APIKEY);
            DadosTemporada dadosTemporada = converterDados.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        temporadas.forEach(System.out::println);
        }
    }

    private void buscarFilmeWeb() {
        System.out.println("Digite o nome do filme para busca:");
        var nomeFilme = scanner.nextLine();
        var json = consumoAPI.ObterDados(ENDERECOURL + nomeFilme.replace(" ", "+") + "&type=movie" + APIKEY);
        DadosSerie dados = converterDados.obterDados(json, DadosSerie.class);
        System.out.println(dados);
    }

    private void listarSeriesBuscadas() {
        dadosSeries.forEach(System.out::println);
    }
}
