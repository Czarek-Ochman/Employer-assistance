package pl.employer.assistance.model.dto;

import lombok.Getter;
import lombok.Setter;
import pl.employer.assistance.model.Address;
import pl.employer.assistance.model.Employee;
import pl.employer.assistance.model.User;

import java.util.List;

@Getter
@Setter
public class CompanyDto {
    private long id;
    private String name;
    private List<Employee> employeeList;
    private Address address;
    private User user;
}