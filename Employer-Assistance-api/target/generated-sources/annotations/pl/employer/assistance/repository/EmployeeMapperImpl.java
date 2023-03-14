package pl.employer.assistance.repository;

import javax.annotation.processing.Generated;
import pl.employer.assistance.model.Employee;
import pl.employer.assistance.model.EmployeeDepartment;
import pl.employer.assistance.model.dto.EmployeeDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-27T13:47:14+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee mapToEmployee(EmployeeDto employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId( employeeDto.getId() );
        if ( employeeDto.getEmployeeDepartment() != null ) {
            employee.setEmployeeDepartment( Enum.valueOf( EmployeeDepartment.class, employeeDto.getEmployeeDepartment() ) );
        }
        employee.setPerson( employeeDto.getPerson() );
        employee.setAddress( employeeDto.getAddress() );
        employee.setSalary( employeeDto.getSalary() );
        employee.setVacationDays( employeeDto.getVacationDays() );
        employee.setSickDays( employeeDto.getSickDays() );
        employee.setIll( employeeDto.isIll() );
        employee.setOnVacation( employeeDto.isOnVacation() );

        return employee;
    }

    @Override
    public EmployeeDto mapToEmployeeDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId( employee.getId() );
        employeeDto.setPerson( employee.getPerson() );
        if ( employee.getEmployeeDepartment() != null ) {
            employeeDto.setEmployeeDepartment( employee.getEmployeeDepartment().name() );
        }
        employeeDto.setAddress( employee.getAddress() );
        employeeDto.setSalary( employee.getSalary() );
        employeeDto.setVacationDays( employee.getVacationDays() );
        employeeDto.setSickDays( employee.getSickDays() );
        employeeDto.setIll( employee.isIll() );
        employeeDto.setOnVacation( employee.isOnVacation() );

        return employeeDto;
    }
}
