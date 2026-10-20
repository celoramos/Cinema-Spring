package br.com.cinema.services.traducao;

import  br.com.cinema.services.ConsumoAPI;
import br.com.cinema.services.ConverterDados;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ConsultaMyMemory {
    public static String obterTraducao(String text) {
        ConsumoAPI consumo = new ConsumoAPI();
        ConverterDados conversor = new ConverterDados();

        String texto = URLEncoder.encode(text, StandardCharsets.UTF_8);
        String langpair = URLEncoder.encode("en|pt-br", StandardCharsets.UTF_8);

        String url = "https://api.mymemory.translated.net/get?q=" + texto + "&langpair=" + langpair;

        String json = consumo.ObterDados(url);

        DadosTraducao traducao = conversor.obterDados(json, DadosTraducao.class);
        // se a API falhar ou a cota acabar, mantém a sinopse original em inglês
        if (traducao == null || traducao.dadosResposta() == null) {
            return text;
        }
        return traducao.dadosResposta().textoTraduzido();
    }
}
