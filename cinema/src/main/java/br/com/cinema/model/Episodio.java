package br.com.cinema.model;
import java.time.LocalDate;

public class Episodio {
    private String titulo;
    private Integer temporadas;
    private Integer numeroEpisodio;
    private Double avaliacaoEpisodio;
    private LocalDate dataLancamento;


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
}
