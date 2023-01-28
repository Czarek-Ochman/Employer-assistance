package pl.employer.assistance.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.employer.assistance.model.Company;
import pl.employer.assistance.model.dto.CompanyDto;
import pl.employer.assistance.service.CompanyService;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/control-panel/company")
    public String sayHello(){
        return "hello";
    }

    @PostMapping("/control-panel/company")
    public Company addCompany(@RequestBody CompanyDto companyDto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
       return companyService.addNewCompany(username,companyDto);
    }

    @GetMapping("/control-panel/company/user/{id}")
    public Boolean userHasCompany(@PathVariable long id){
        return companyService.getCompanyByUser(id);
    }

    @GetMapping("/control-panel/get/company/user/{id}")
    public CompanyDto getCompanyByUserId(@PathVariable long id){
        return companyService.getCompanyByUserId(id);
    }
}
