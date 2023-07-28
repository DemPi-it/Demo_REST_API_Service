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

@RestController
@RequestMapping("films")
public class FilmController extends BaseCrudController<Film, FilmRepository> {
    @Autowired
    public FilmController(FilmRepository repository) {
        super(repository);
    }
}
