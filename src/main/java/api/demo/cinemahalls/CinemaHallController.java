package api.demo.cinemahalls;

import api.demo.BaseCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("halls")
public class CinemaHallController extends BaseCrudController<CinemaHall, HallsRepository> {
    @Autowired
    public CinemaHallController(HallsRepository repository) {
        super(repository);
    }

}
