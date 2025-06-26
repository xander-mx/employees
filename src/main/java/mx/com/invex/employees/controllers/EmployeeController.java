package mx.com.invex.employees.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import mx.com.invex.employees.dtos.request.EmployeeRequest;
import mx.com.invex.employees.services.IEmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static mx.com.invex.employees.constants.PathConstants.EMPLOYEES_PATH;

@RestController
@RequestMapping(value = EMPLOYEES_PATH)
@RequiredArgsConstructor
public class EmployeeController {

    private final IEmployeeService employeeService;

    @Operation(summary = "Devuelve todos los empleados", description = "Obtiene todos los empleados registrados")
    @GetMapping
    public ResponseEntity<Object> getEmployees() {
        return this.employeeService.getAllEmployees();
    }

    @Operation(summary = "Devuelve un empleado por medio de su id", description = "Obtiene un empleado por el id de registro")
    @GetMapping(value = "/{id:\\d+}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable Long id) {
        return this.employeeService.getEmployee(id);
    }

    @Operation(summary = "Busqueda de empleado por nombre", description = "Busca un empleado a partir de su nombre")
    @GetMapping(value = "/search")
    public ResponseEntity<Object> getEmployeesSearch(@RequestParam String name) {
        return  this.employeeService.getEmployeeSearch(name);
    }

    @Operation(summary = "Creación de empleado", description = "Registra un nuevo empleado")
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody EmployeeRequest request) {
        return this.employeeService.save(request);
    }

    @Operation(summary = "Actualización de empleado", description = "Actualiza los datos de un empleado")
    @PutMapping(value = "/{id:\\d+}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody EmployeeRequest request) {
        return this.employeeService.update(id, request);
    }

    @Operation(summary = "Eliminación de empleado por id", description = "Elimina un empleado por medio de su id de registro")
    @DeleteMapping(value = "/{id:\\d+}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return this.employeeService.delete(id);
    }
}
