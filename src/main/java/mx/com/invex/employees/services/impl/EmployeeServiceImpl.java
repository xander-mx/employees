package mx.com.invex.employees.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.com.invex.employees.dtos.EmployeeDTO;
import mx.com.invex.employees.dtos.request.EmployeeRequest;
import mx.com.invex.employees.dtos.response.GenericResponse;
import mx.com.invex.employees.mappers.EmployeeMapper;
import mx.com.invex.employees.models.Employee;
import mx.com.invex.employees.repositories.EmployeeRepository;
import mx.com.invex.employees.services.IEmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper = EmployeeMapper.getInstance();

    @Override
    public ResponseEntity<Object> getAllEmployees() {
        GenericResponse.Data<EmployeeDTO> data = GenericResponse.Data.<EmployeeDTO>builder()
                .attributes(employeeMapper.mapperToEmployeeDto(employeeRepository.findAll())).build();
        GenericResponse<EmployeeDTO> response = GenericResponse.<EmployeeDTO>builder().data(data).build();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Object> getEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()) {
            EmployeeDTO employeeDTO = employeeMapper.mapperToEmployeeDto(employee.get());
            GenericResponse.Data<EmployeeDTO> data = GenericResponse.Data.<EmployeeDTO>builder()
                    .attributes(List.of(employeeDTO)).build();
            GenericResponse<EmployeeDTO> response = GenericResponse.<EmployeeDTO>builder().data(data).build();
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Object> save(EmployeeRequest request) {
        Employee employee = employeeMapper.mapperToEmployee(request);
        EmployeeDTO employeeDTO = employeeMapper.mapperToEmployeeDto(employeeRepository.save(employee));
        GenericResponse.Data<EmployeeDTO> data = GenericResponse.Data.<EmployeeDTO>builder()
                .attributes(List.of(employeeDTO)).build();
        GenericResponse<EmployeeDTO> response = GenericResponse.<EmployeeDTO>builder().data(data).build();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Object> update(Long id, EmployeeRequest request) {
        if(employeeRepository.existsById(id)) {
            Employee employee = employeeMapper.mapperToEmployee(request);
            employee.setId(id);
            EmployeeDTO employeeDTO = employeeMapper.mapperToEmployeeDto(employeeRepository.save(employee));
            GenericResponse.Data<EmployeeDTO> data = GenericResponse.Data.<EmployeeDTO>builder()
                    .attributes(List.of(employeeDTO)).build();
            GenericResponse<EmployeeDTO> response = GenericResponse.<EmployeeDTO>builder().data(data).build();
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Object> delete(Long id) {
        if(employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            GenericResponse.Data<String> data = GenericResponse.Data.<String>builder()
                    .attributes(List.of("Eliminado exitosamente!!")).build();
            GenericResponse<String> response = GenericResponse.<String>builder().data(data).build();
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Object> getEmployeeSearch(String search) {
        List<Employee> employees = employeeRepository.findByFirstNameLike(search);
        if(employees.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        GenericResponse.Data<EmployeeDTO> data = GenericResponse.Data.<EmployeeDTO>builder()
                .attributes(employeeMapper.mapperToEmployeeDto(employees)).build();
        GenericResponse<EmployeeDTO> response = GenericResponse.<EmployeeDTO>builder().data(data).build();
        return  ResponseEntity.ok(response);
    }
}
