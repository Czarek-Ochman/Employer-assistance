package pl.employer.assistance.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import pl.employer.assistance.model.Address;
import pl.employer.assistance.model.EmployeeDepartment;
import pl.employer.assistance.model.Person;

import javax.persistence.*;

@Getter
@Setter
public class EmployeeDto {
    private long id;
    private Person person;
    private String employeeDepartment;
    private Address address;
    private double salary;
    private int vacationDays;
    private int sickDays;
}
