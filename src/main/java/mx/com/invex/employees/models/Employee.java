package mx.com.invex.employees.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
    private Long id;

    private String firstName;
    private String secondName;
    private String lastName;
    private String secondLastName;
    private Integer age;
    private String gender;
    private LocalDate birthDate;
    private String jobTitle;
    @Column(name = "created_date", insertable = false, updatable = false)
    private LocalDate createdDate;
    private Boolean status;
}
