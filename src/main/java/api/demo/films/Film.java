package api.demo.films;

import api.demo.tickets.Ticket;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="films")
public class Film {
    @Id
    @Column(name="film_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name="film_title")
    private String filmTitle;
    @Column(name="release_date")
    private String releaseDate;
    @Column(name="genre")
    private String genre;
    @Column(name="duration")
    private Integer duration;
    @OneToMany(mappedBy = "films")
    List<Ticket> tickets;


    public Film(String filmTitle, String releaseDate, String genre, Integer duration) {
        this.filmTitle = filmTitle;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Film film = (Film) o;
        return id != null && Objects.equals(id, film.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
