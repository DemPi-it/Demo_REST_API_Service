package api.demo.employee;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@EqualsAndHashCode
public class EmployeeEntity {
    public static AtomicInteger nextInt = new AtomicInteger();
    @EqualsAndHashCode.Exclude
    private Integer id;
    private String firstName;
    private String secondName;
    private String middleName;
    private String phoneNumber;
    private String email;
    private String address;

    public EmployeeEntity(String firstName, String secondName,
                          String middleName, String phoneNumber,
                          String email, String address) {
        id = nextInt.incrementAndGet();
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public void switchInfo(EmployeeEntity employee){
        setFirstName(employee.getFirstName());
        setSecondName(employee.getSecondName());
        setMiddleName(employee.getMiddleName());
        setPhoneNumber(employee.getPhoneNumber());
        setEmail(employee.getEmail());
        setAddress(employee.getAddress());
    }
}
