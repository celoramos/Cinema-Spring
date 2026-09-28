package br.com.cinema.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(@JsonAlias("Title") String Title,
                         @JsonAlias("Season") Integer Season,
                         @JsonAlias("totalSeasons") Integer totalTemporadas,
                         @JsonAlias("Released") String Released,
                         @JsonAlias("imdbRating") String imdbRating) {
}
