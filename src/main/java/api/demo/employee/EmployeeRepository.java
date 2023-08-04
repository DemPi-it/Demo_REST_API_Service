package api.demo.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public Employee findByEmail(String email);
    public Employee findByPhoneNumber(String phoneNumber);
    public ResponseEntity<?> deleteByEmail(String email);
    public ResponseEntity<?> deleteByPhoneNumber(String phoneNumber);
}
