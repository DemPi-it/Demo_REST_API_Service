package api.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

public class BaseCrudController<E, R extends JpaRepository<E, Integer>> {
    protected final R repository;
    public BaseCrudController(R repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<E> getAll(){
        return repository.findAll();
    }

    @GetMapping("/id")
    public Optional<E> getById(@RequestParam Integer id){
        return repository.findById(id);
    }

    @PostMapping("/add")
    public E add(@RequestBody E entity){
        return repository.save(entity);
    }


}
