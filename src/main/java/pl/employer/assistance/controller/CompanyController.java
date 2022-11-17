package pl.employer.assistance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.employer.assistance.service.CompanyService;

@RestController
public class CompanyController {



    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/jaja")
    public ResponseEntity<String> getHello(){
        return ResponseEntity.ok("Ale jaja");
    }
}
