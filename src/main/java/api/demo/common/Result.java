package api.demo.common;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.function.Function;

public class Result<TEntity, TError extends ValidationError>{
    private TEntity entity;
    private TError error;
    private final boolean isError;
    private boolean isSuccess() {
        return !isError;
    }

    public Result(
            Optional<TEntity> entity,
            TError error
    ) {
        if (entity.isPresent()) {
            this.entity = entity.get();
            this.isError = false;
        } else {
            this.isError = true;
            this.error = error;
        }
    }

    public ResponseEntity<?> resolve(
            Function<TEntity, ResponseEntity<TEntity>> success,
            Function<TError, ResponseEntity<TError>> fail
    ) {
        return isSuccess() ? success.apply(entity) : fail.apply(error);
    }
}
