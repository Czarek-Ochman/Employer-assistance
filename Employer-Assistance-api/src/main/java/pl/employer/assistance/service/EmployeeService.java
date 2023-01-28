package pl.employer.assistance.service;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import pl.employer.assistance.model.Company;
import pl.employer.assistance.model.Employee;
import pl.employer.assistance.model.EmployeeDepartment;
import pl.employer.assistance.model.User;
import pl.employer.assistance.model.dto.EmployeeDto;
import pl.employer.assistance.model.mapper.CompanyMapper;
import pl.employer.assistance.repository.CompanyRepository;
import pl.employer.assistance.repository.EmployeeMapper;
import pl.employer.assistance.repository.EmployeeRepository;
import pl.employer.assistance.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper mapper = Mappers.getMapper(EmployeeMapper.class);
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    public EmployeeService(EmployeeRepository employeeRepository, CompanyRepository companyRepository, UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    public EmployeeDto addEmployee(String username, EmployeeDto employeeDto) {
        User user = userRepository.findByEmail(username);
        Company company = companyRepository.findByUser(user);
        Employee employee = mapper.mapToEmployee(employeeDto);
        employee.setCompany(company);
        employeeRepository.save(employee);
        return employeeDto;
    }

    public List<EmployeeDto> getAllEmployeeByCompanyId(String username) {
        User user = userRepository.findByEmail(username);
        Company company = companyRepository.findByUser(user);
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        List<Employee> employeeList = employeeRepository.getAllByCompany(company);
        for (Employee e : employeeList) {
            employeeDtos.add(mapper.mapToEmployeeDto(e));
        }
        return employeeDtos;
    }

    public boolean deleteById(long id) {
        try {
            employeeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
