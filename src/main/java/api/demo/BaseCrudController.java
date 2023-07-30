package api.demo;

import api.demo.common.Result;
import api.demo.common.ValidationError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static api.demo.common.ResponseCallbacks.notFoundResponse;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.ok;

public class BaseCrudController<E, R extends JpaRepository<E, Integer>> {
    protected final R repository;
    public BaseCrudController(R repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<E> getAll(){
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return new Result<>(
                repository.findById(id),
                new ValidationError("Not found entity by id " + id)
        ).resolve(
                ResponseEntity::ok,
                (e) -> ResponseEntity.status(NOT_FOUND).body(e)
        );
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody E entity){
        return new Result<>(
                entity!=null ? Optional.of(entity) : Optional.empty(),
                new ValidationError("No entity in request body")
        ).resolve(
                body -> ok(repository.save(entity)),
                (e) -> ResponseEntity.status(NOT_FOUND).body(e)
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> removeById(@PathVariable Integer id){
        var entity = repository.findById(id);
        if(entity.isEmpty()) return notFoundResponse(id);
        repository.delete(entity.get());
        return ok("Deleted successful");
    }

}
