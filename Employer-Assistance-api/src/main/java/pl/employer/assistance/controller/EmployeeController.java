package pl.employer.assistance.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.employer.assistance.model.Employee;
import pl.employer.assistance.model.dto.EmployeeDto;
import pl.employer.assistance.repository.UserRepository;
import pl.employer.assistance.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final UserRepository userRepository;

    public EmployeeController(EmployeeService employeeService, UserRepository userRepository) {
        this.employeeService = employeeService;
        this.userRepository = userRepository;
    }

    @PostMapping("/control-panel/employee")
    public EmployeeDto addNewEmployee(@RequestBody EmployeeDto employeeDto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return employeeService.addEmployee(username,employeeDto);
    }

    @GetMapping("/control-panel/employee/company")
    public List<EmployeeDto> getAllEmployeeByCompanyId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return employeeService.getAllEmployeeByCompanyId(username);
    }

    @DeleteMapping("/control-panel/employee/{id}")
    public boolean deleteById(@PathVariable long id){
      return  employeeService.deleteById(id);
    }

    @GetMapping("/control-panel/employee/{id}")
    public Employee getEmployeeById(@PathVariable long id){
        return  employeeService.getEmployeeById(id);
    }

    @PutMapping("/control-panel/employee/edit")
    public Employee editEmployee(@RequestBody EmployeeDto employeeDto){
        return employeeService.editEmployee(employeeDto);
    }
}