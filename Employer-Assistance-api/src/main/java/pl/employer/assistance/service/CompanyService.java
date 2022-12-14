package pl.employer.assistance.service;

import org.springframework.stereotype.Service;
import pl.employer.assistance.repository.CompanyRepository;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }



}
