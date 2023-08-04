package api.demo.films;

import api.demo.BaseCrudController;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("films")
public class FilmController extends BaseCrudController<Film, FilmRepository> {
    @Autowired
    public FilmController(FilmRepository repository) {
        super(repository);
    }

    @GetMapping("/genre/{genre}")
    public List<Film> getByGenre(@PathVariable String genre){
        return repository.findByGenre(genre);
    }
    @GetMapping("/title/{title}")
    public Film getByTitle(@PathVariable String title){
        return repository.findByFilmTitle(title);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateFilmInfo(@PathVariable Integer id, @RequestBody Film film){
        var oldFilm = repository.findById(id);
        if(oldFilm.isEmpty()){ return badRequest().body("No entity with this id "+id); }
        Film oldFilmInfo = oldFilm.get();
        oldFilmInfo.setFilmId(id);
        oldFilmInfo.setFilmTitle(film.getFilmTitle());
        oldFilmInfo.setDuration(film.getDuration());
        oldFilmInfo.setGenre(film.getGenre());
        oldFilmInfo.setReleaseDate(film.getReleaseDate());
        repository.save(oldFilmInfo);
        return ok(oldFilmInfo);
    }

    @DeleteMapping("/delete/{title}")
    public ResponseEntity<?> removeByTitle(@PathVariable String title){
        var film = repository.findByFilmTitle(title);
        if(film == null){
            return badRequest().body("not found film with this title: " + title);
        }
        repository.deleteByFilmTitle(title);
        return ok("successful deleted");
    }
}
