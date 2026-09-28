package br.com.cinema.model;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {
    private String titulo;
    private Integer temporadas;
    private Integer numeroEpisodio;
    private Double avaliacaoEpisodio;
    private LocalDate dataLancamento;

public Episodio (Integer numeroTemporadas, DadosEpisodio dadosEpisodio) {
    this.temporadas = numeroTemporadas;
    this.titulo = dadosEpisodio.Title();
    this.numeroEpisodio = dadosEpisodio.numeroEps();
    try {
    this.avaliacaoEpisodio = Double.valueOf(dadosEpisodio.avaliacaoImdb());
    } catch (NumberFormatException e) {
        this.avaliacaoEpisodio = 0.0;
    }
    try {
        this.dataLancamento = LocalDate.parse(dadosEpisodio.dataLancamento());
    } catch (DateTimeParseException exception) {
        this.dataLancamento = null;
    }
}


    public void setTitulo(String titulo) {this.titulo = titulo;}
    public void setTemporadas(Integer temporadas) {this.temporadas = temporadas;}
    public void setNumeroEpisodio(Integer numeroEpisodio) {this.numeroEpisodio = numeroEpisodio;}
    public void setAvaliacaoEpisodio(Double avaliacaoEpisodio) {this.avaliacaoEpisodio = avaliacaoEpisodio;}
    public void setDataLancamento(LocalDate dataLancamento) {this.dataLancamento = dataLancamento;}


    public String getTitulo() {return titulo;}
    public Integer getTemporadas() {return temporadas;}
    public Integer getNumeroEpisodio() {return numeroEpisodio;}
    public Double getAvaliacaoEpisodio() {return avaliacaoEpisodio;}
    public LocalDate getDataLancamento() {return dataLancamento;}


    @Override
    public String toString() {
        return "titulo='" + titulo + '\'' +
                ", temporadas=" + temporadas +
                ", numeroEpisodio=" + numeroEpisodio +
                ", avaliacaoEpisodio=" + avaliacaoEpisodio +
                ", dataLancamento=" + dataLancamento;
    }
}

