package api.demo.employee;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    List<EmployeeEntity> employeeList = new ArrayList<>();

    @GetMapping("/all")
    public List<EmployeeEntity> getAllEmployeeList(){
        return employeeList;
    }

    @GetMapping("/id")
    public List<EmployeeEntity> getEmployeeById(@RequestParam Integer id){
        return employeeList.stream().filter(x -> x.getId().equals(id)).toList();
    }

    @GetMapping("/phone")
    public List<EmployeeEntity> getEmployeeByPhone(@RequestParam String phone){
        return employeeList.stream().filter(x -> x.getPhoneNumber().equals(phone)).toList();
    }


    @PostMapping("/add")
    public ResponseEntity<?> addNewEmployee(@RequestBody EmployeeEntity employee){
        if(employeeList.contains(employee)){
            EmployeeEntity.nextInt.decrementAndGet();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Такой сотрудник уже существует");
        }
        employeeList.add(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmployeeInfo(@RequestParam Integer id, @RequestBody EmployeeEntity employee){
        var oldEmployeeInfo = employeeList.stream().filter(x -> x.getId().equals(id)).findFirst();
        if(oldEmployeeInfo.isEmpty()){
            EmployeeEntity.nextInt.decrementAndGet();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Такого сотрудника не существует");
        }
        EmployeeEntity.nextInt.decrementAndGet();
        EmployeeEntity oldEmployee = oldEmployeeInfo.get();
        oldEmployee.switchInfo(employee);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteEmployeeByID(@RequestParam Integer id){
        var employeeToDelete = employeeList.stream().filter(x -> x.getId().equals(id)).findFirst();
        if(employeeToDelete.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Сотрудника с таким ID не существует");
        }
        employeeList.remove(employeeToDelete.get());
        EmployeeEntity.nextInt.decrementAndGet();
        return ResponseEntity.status(HttpStatus.OK).body("Сотрудник успешно удален");
    }
}
