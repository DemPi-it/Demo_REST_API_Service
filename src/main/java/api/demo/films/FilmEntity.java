package api.demo.films;

import lombok.*;

import java.util.concurrent.atomic.AtomicInteger;


@Getter
@Setter
@EqualsAndHashCode
public class FilmEntity {
    static AtomicInteger nextId = new AtomicInteger();
    @EqualsAndHashCode.Exclude
    private Integer id;
    private String title;
    private String yearOfRelease;
    private String genre;
    private Integer duration;

    public FilmEntity(String title, String yearOfRelease, String genre, Integer duration) {
        id = nextId.incrementAndGet();
        this.title = title;
        this.yearOfRelease = yearOfRelease;
        this.genre = genre;
        this.duration = duration;
    }
}
