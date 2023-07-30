package api.demo.common;

import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class ResponseCallbacks {
    public static ResponseEntity<String> notFoundResponse(Integer id){
        return ResponseEntity
                .status(NOT_FOUND)
                .body("Not found entity with this id" + id);
    }
}
