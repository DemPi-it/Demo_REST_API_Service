package api.demo.films;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("films")
public class FilmController {
    private List<FilmEntity> listOfFilms = new ArrayList<>();

    @GetMapping("")
    public ResponseEntity<?> getAllFilms(){
        if(listOfFilms.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("На данный момент фильмов нет в базе данных");
        }
        return ResponseEntity.status(HttpStatus.OK).body(listOfFilms);
    }

    @GetMapping("/genre")
    public List<FilmEntity> getFilmsByGenre(@RequestParam String genre){
        return listOfFilms.stream().filter(x -> x.getGenre().contains(genre)).toList();
    }

    @GetMapping("/id")
    public List<FilmEntity> getFilmById(@RequestParam Integer id){
        return listOfFilms.stream().filter(x -> x.getId().equals(id)).toList();
    }

    @GetMapping("/title")
    public List<FilmEntity> getFilmByTitle(@RequestParam String title){
        return listOfFilms.stream().filter(x->x.getTitle().contains(title)).toList();
    }

    @PostMapping(value = "/addfilm", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addFilmToList(@RequestBody FilmEntity film){
        if(listOfFilms.contains(film)){
            FilmEntity.nextId.decrementAndGet();
            return ResponseEntity.badRequest().body("Фильм уже существует");
        }
        listOfFilms.add(film);
        return ResponseEntity.status(HttpStatus.CREATED).body(film);
    }


    @DeleteMapping("/delete/id")
    public ResponseEntity<?> deleteFilmById(@RequestParam Integer id){
        if(listOfFilms.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("На данный момент фильмов нет в базе данных");
        }
        Optional<FilmEntity> deleteFilm = listOfFilms.stream().filter(x -> x.getId().equals(id)).findFirst();
        if(deleteFilm.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Фильма с таким ID не существует");
        }
        FilmEntity film = deleteFilm.get();
        listOfFilms.remove(film);
        listOfFilms.stream().filter(x->x.getId()>id).forEach(x->x.setId(x.getId()-1));
        FilmEntity.nextId.decrementAndGet();
        return ResponseEntity.status(HttpStatus.OK).body("Фильм успешно удален");
    }
    @DeleteMapping("/delete/title")
    public ResponseEntity<?> deleteFilmByTitle(@RequestParam String title){
        if(listOfFilms.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("На данный момент фильмов нет в базе данных");
        }
        Optional<FilmEntity> deleteFilm = listOfFilms.stream().filter(x -> x.getTitle().equals(title)).findFirst();
        if(deleteFilm.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Фильма с таким ID не существует");
        }
        FilmEntity film = deleteFilm.get();
        listOfFilms.remove(film);
        listOfFilms.stream().filter(x->x.getId()>film.getId()).forEach(x->x.setId(x.getId()-1));
        FilmEntity.nextId.decrementAndGet();
        return ResponseEntity.status(HttpStatus.OK).body("Фильм успешно удален");
    }

    @PutMapping("/put/id")
    public ResponseEntity<?> putBookById(@RequestParam Integer id, @RequestBody FilmEntity film){
        var oldFilm = listOfFilms.stream().filter(x -> x.getId().equals(id)).findFirst();
        if(oldFilm.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Такого фильма не существует");
        }
        FilmEntity.nextId.decrementAndGet();
        FilmEntity oldFilmToPut = oldFilm.get();
        oldFilmToPut.switchFilm(film);
        return ResponseEntity.status(HttpStatus.OK).body(film);
    }
}
