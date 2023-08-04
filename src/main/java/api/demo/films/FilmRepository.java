package api.demo.films;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {
    public Film findByFilmTitle(String title);
    public List<Film> findByGenre(String genre);
    public ResponseEntity<?> deleteByFilmTitle(String title);
}
