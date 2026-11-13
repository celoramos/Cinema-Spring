package br.com.cinema.model;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import br.com.cinema.services.traducao.ConsultaMyMemory;
import jakarta.persistence.*;

@Entity
@Table(name = "series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String Titulo;
    private Integer Season;
    private String Released;
    private Double imdbRating;
    private Integer totalTemporadas;
@Enumerated(EnumType.STRING)
    private String Atores;
    private String Sinopse;
    private String Posters;
    private Categoria Genero;

    private List<Episodio> episodios = new ArrayList<>();
    public List<Episodio> getEpisodios() {return episodios;}
    public void setEpisodios(List<Episodio> episodios) {this.episodios = episodios;}



    public Serie(DadosSerie dadosSerie) {
        this.Titulo = dadosSerie.Title();
        this.totalTemporadas = dadosSerie.totalTemporadas();
        this.imdbRating = OptionalDouble.of(Double.valueOf(dadosSerie.imdbRating())).orElse(0);
        this.Genero = Categoria.fromString(dadosSerie.Genero().split(", ")[0].trim());
        this.Atores = dadosSerie.atores();
        this.Posters = dadosSerie.poster();
        this.Sinopse = ConsultaMyMemory.obterTraducao(dadosSerie.sinopse()).trim();
    }



    public Long getId() {return id;}
    public String getTitulo() {return Titulo;}
    public String getAtores() {return Atores;}
    public Integer getSeason() {return Season;}
    public String getPosters() {return Posters;}
    public String getSinopse() {return Sinopse;}
    public Categoria getGenero() {return Genero;}
    public String getReleased() {return Released;}
    public Double getImdbRating() {return imdbRating;}
    public Integer getTotalTemporadas() {return totalTemporadas;}


    public void setId(Long id) {this.id = id;}
    public void setTitulo(String titulo) {this.Titulo = titulo;}
    public void setAtores(String atores) {this.Atores = atores;}
    public void setSeason(Integer season) {this.Season = season;}
    public void setGenero(Categoria genero) {this.Genero = genero;}
    public void setPosters(String posters) {this.Posters = posters;}
    public void setSinopse(String sinopse) {this.Sinopse = sinopse;}
    public void setReleased(String released) {this.Released = released;}
    public void setImdbRating(Double imdbRating) {this.imdbRating = imdbRating;}
    public void setTotalTemporadas(Integer totalTemporadas) {this.totalTemporadas = totalTemporadas;}

    
    @Override
    public String toString() {
        return "Titulo='" + Titulo + '\'' +
                ", Atores='" + Atores + '\'' +
                ", Posters='" + Posters + '\'' +
                ", Sinopse='" + Sinopse + '\'' +
                ", Season=" + Season +
                ", Released='" + Released + '\'' +
                ", Genero=" + Genero +
                ", imdbRating=" + imdbRating +
                ", totalTemporadas=" + totalTemporadas;
    }
}
