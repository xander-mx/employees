package mx.com.invex.employees.dtos.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeRequest {

    private String name;
    private String surname;
    private String lastName;
    private String secondLastName;
    private Integer age;
    private String preferedGender;
    private LocalDate birthdayDate;
    private String title;
    private Boolean statusEmployee;
}
