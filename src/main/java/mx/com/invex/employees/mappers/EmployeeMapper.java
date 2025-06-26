package mx.com.invex.employees.mappers;

import mx.com.invex.employees.dtos.EmployeeDTO;
import mx.com.invex.employees.dtos.request.EmployeeRequest;
import mx.com.invex.employees.models.Employee;

import java.util.List;

public class EmployeeMapper {

    private static EmployeeMapper instance = null;

    public static EmployeeMapper getInstance() {
        if (instance == null) {
            instance = new EmployeeMapper();
        }
        return instance;
    }

    public EmployeeDTO mapperToEmployeeDto(Employee employee) {
        return EmployeeDTO.builder().employeeId(employee.getId())
                .firstName(employee.getFirstName()).secondName(employee.getSecondName())
                .lastName(employee.getLastName()).secondLastName(employee.getSecondLastName())
                .birthDate(employee.getBirthDate()).age(employee.getAge())
                .jobTitle(employee.getJobTitle()).gender(employee.getGender())
                .createdDate(employee.getCreatedDate()).status(employee.getStatus()).build();
    }

    public List<EmployeeDTO> mapperToEmployeeDto(List<Employee> employees) {
        return employees.stream().map(this::mapperToEmployeeDto).toList();
    }

    public Employee mapperToEmployee(EmployeeRequest request) {
        return Employee.builder().firstName(request.getName()).secondName(request.getSurname())
                .lastName(request.getLastName()).secondLastName(request.getSecondLastName())
                .age(request.getAge()).gender(request.getPreferedGender()).jobTitle(request.getTitle())
                .birthDate(request.getBirthdayDate()).status(request.getStatusEmployee()).build();
    }
}
