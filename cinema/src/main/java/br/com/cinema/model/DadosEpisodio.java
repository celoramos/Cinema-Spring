package br.com.cinema.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(@JsonAlias("Title") String Title,
                            @JsonAlias("Episode") Integer numeroEps,
                            @JsonAlias("imdbRating") String avaliacaoImdb) {
}
