package pl.employer.assistance.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.employer.assistance.model.Company;
import pl.employer.assistance.model.dto.CompanyDto;
import pl.employer.assistance.service.CompanyService;

@RestController
@RequestMapping("/control-panel")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/company")
    public Company addCompany(@RequestBody CompanyDto companyDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return companyService.addNewCompany(username, companyDto);
    }

    @GetMapping("/user/{id}")
    public Boolean userHasCompany(@PathVariable long id) {
        return companyService.getCompanyByUser(id);
    }

    @GetMapping("/company/user/{id}")
    public CompanyDto getCompanyByUserId(@PathVariable long id) {
        return companyService.getCompanyByUserId(id);
    }
}