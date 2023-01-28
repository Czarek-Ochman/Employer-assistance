package pl.employer.assistance.repository;

import org.mapstruct.Mapper;
import pl.employer.assistance.model.Employee;
import pl.employer.assistance.model.dto.EmployeeDto;

@Mapper
public interface EmployeeMapper {

    Employee mapToEmployee(EmployeeDto employeeDto);

    EmployeeDto mapToEmployeeDto(Employee employee);
}
