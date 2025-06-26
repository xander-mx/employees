package mx.com.invex.employees.repositories;

import mx.com.invex.employees.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByFirstNameLike(String name);
}
