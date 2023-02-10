package pl.employer.assistance.model.dto;

import lombok.Getter;
import lombok.Setter;
import pl.employer.assistance.model.Address;
import pl.employer.assistance.model.Person;


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
    private boolean isIll;
    private boolean isOnVacation;
}