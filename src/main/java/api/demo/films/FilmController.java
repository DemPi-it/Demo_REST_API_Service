package api.demo.films;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("films")
public class FilmController {
    private List<FilmEntity> listOfFilms = new ArrayList<>();

    @GetMapping("/list")
    public List<FilmEntity> getAllFilms(){
        return listOfFilms;
    }

    @GetMapping("/list/genre")
    public List<FilmEntity> getFilmByGenre(@RequestParam String genre){
        return listOfFilms.stream().filter(x -> x.getGenre().contains(genre)).toList();
    }

    @PostMapping(value = "/addfilm", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addFilmToList(@RequestBody FilmEntity film){
        if(listOfFilms.contains(film)){
            FilmEntity.nextId.decrementAndGet();
            return ResponseEntity.badRequest().body("Книга уже существует");
        }
        listOfFilms.add(film);
        return ResponseEntity.status(HttpStatus.CREATED).body(film);
    }
}
