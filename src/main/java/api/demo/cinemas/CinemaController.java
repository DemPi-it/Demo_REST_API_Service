package api.demo.cinemas;

import api.demo.BaseCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cinemas")
public class CinemaController extends BaseCrudController<Cinema, CinemaRepository> {
    @Autowired
    public CinemaController(CinemaRepository repository) {
        super(repository);
    }
}
