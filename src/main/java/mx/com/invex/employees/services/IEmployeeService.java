package mx.com.invex.employees.services;

import mx.com.invex.employees.dtos.request.EmployeeRequest;
import org.springframework.http.ResponseEntity;

public interface IEmployeeService {

    ResponseEntity<Object> getAllEmployees();
    ResponseEntity<Object> getEmployee(Long id);
    ResponseEntity<Object> save(EmployeeRequest request);
    ResponseEntity<Object> update(Long id, EmployeeRequest request);
    ResponseEntity<Object> delete(Long id);
    ResponseEntity<Object> getEmployeeSearch(String search);

}
