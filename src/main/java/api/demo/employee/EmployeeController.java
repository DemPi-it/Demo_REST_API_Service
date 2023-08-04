package api.demo.employee;

import api.demo.BaseCrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("employee")
public class EmployeeController extends BaseCrudController<Employee, EmployeeRepository> {
    @Autowired
    public EmployeeController(EmployeeRepository repository) {
        super(repository);
    }

    @GetMapping("/{phoneNumber}")
    public Employee getByPhoneNumber(@PathVariable String phoneNumber){
        return repository.findByPhoneNumber(phoneNumber);
    }
    @GetMapping("/{email}")
    public Employee getByEmail(@PathVariable String email){
        return repository.findByEmail(email);
    }
    @DeleteMapping("/delete/{phoneNumber}")
    public ResponseEntity<?> removeByPhoneNumber(@PathVariable String phoneNumber){
        var employee = repository.findByPhoneNumber(phoneNumber);
        if(employee == null){ return badRequest().body("no employee with that phone number: " + phoneNumber); }
        repository.deleteByPhoneNumber(phoneNumber);
        return ok("successful deleted");
    }
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<?> removeByEmail(@PathVariable String email){
        var employee = repository.findByEmail(email);
        if(employee == null){ return badRequest().body("no employee with that phone number: " + email); }
        repository.deleteByEmail(email);
        return ok("successful deleted");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployeeInfo(@PathVariable Integer id, @RequestBody Employee employee){
        var oldEmployee = repository.findById(id);
        if(oldEmployee.isEmpty()){ return badRequest().body("No entity with this id "+id); }
        Employee oldEmployeeInfo = oldEmployee.get();
        oldEmployeeInfo.setEmployeeId(id);
        oldEmployeeInfo.setEmail(employee.getEmail());
        oldEmployeeInfo.setFirstName(employee.getFirstName());
        oldEmployeeInfo.setMiddleName(employee.getMiddleName());
        oldEmployeeInfo.setSecondName(employee.getSecondName());
        oldEmployeeInfo.setPhoneNumber(employee.getPhoneNumber());
        oldEmployeeInfo.setAddress(employee.getAddress());
        repository.save(oldEmployeeInfo);
        return ok(oldEmployeeInfo);
    }
}
