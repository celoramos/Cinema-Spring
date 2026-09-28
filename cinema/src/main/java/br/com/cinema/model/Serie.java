package br.com.cinema.model;

import java.util.OptionalDouble;

public class Serie {
    private String Titulo;
    private String Atores;
    private String Posters;
    private String Sinopse;
    private Integer Season;
    private String Released;
    private Categoria Genero;
    private Double imdbRating;
    private Integer totalTemporadas;

    public Serie(DadosSerie dadosSerie) {
        this.Titulo = dadosSerie.Title();
        this.totalTemporadas = dadosSerie.totalTemporadas();
        this.imdbRating = OptionalDouble.of(Double.valueOf(dadosSerie.imdbRating())).orElse(0);
        this.Genero = Categoria.fromString(dadosSerie.Genero().split(", ")[0].trim());
        this.Atores = dadosSerie.atores();
        this.Posters = dadosSerie.poster();
        this.Sinopse = dadosSerie.sinopse();
    }

    public void setTitulo(String titulo) {this.Titulo = titulo;}
    public void setAtores(String atores) {this.Atores = atores;}
    public void setPosters(String posters) {this.Posters = posters;}
    public void setSinopse(String sinopse) {this.Sinopse = sinopse;}
    public void setSeason(Integer season) {this.Season = season;}
    public void setReleased(String released) {this.Released = released;}
    public void setGenero(Categoria genero) {this.Genero = genero;}
    public void setImdbRating(Double imdbRating) {this.imdbRating = imdbRating;}
    public void setTotalTemporadas(Integer totalTemporadas) {this.totalTemporadas = totalTemporadas;}


    public String getTitulo() {return Titulo;}
    public String getAtores() {return Atores;}
    public String getPosters() {return Posters;}
    public String getSinopse() {return Sinopse;}
    public Integer getSeason() {return Season;}
    public String getReleased() {return Released;}
    public Enum<Categoria> getGenero() {return Genero;}
    public Double getImdbRating() {return imdbRating;}
    public Integer getTotalTemporadas() {return totalTemporadas;}
}
