package api.demo.employee;

import api.demo.BaseCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController extends BaseCrudController<Employee, EmployeeRepository> {
    @Autowired
    public EmployeeController(EmployeeRepository repository) {
        super(repository);
    }
}
