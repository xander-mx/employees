package mx.com.invex.employees.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class EmployeeDTO {

    private Long employeeId;
    private String firstName;
    private String secondName;
    private String lastName;
    private String secondLastName;
    private Integer age;
    private String gender;
    private LocalDate birthDate;
    private String jobTitle;
    private LocalDate createdDate;
    private Boolean status;
}
