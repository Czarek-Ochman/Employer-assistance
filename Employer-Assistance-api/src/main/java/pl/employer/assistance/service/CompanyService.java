package pl.employer.assistance.service;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import pl.employer.assistance.model.Company;
import pl.employer.assistance.model.User;
import pl.employer.assistance.model.dto.CompanyDto;
import pl.employer.assistance.model.dto.UserDto;
import pl.employer.assistance.model.mapper.CompanyMapper;
import pl.employer.assistance.model.mapper.UserMapper;
import pl.employer.assistance.repository.CompanyRepository;
import pl.employer.assistance.repository.UserRepository;
import pl.employer.assistance.service.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final UserService userService;
//    private final Add
    private final CompanyMapper mapper = Mappers.getMapper(CompanyMapper.class);

    public CompanyService(CompanyRepository companyRepository, UserRepository userRepository, UserService userService) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public Company addNewCompany(String username, CompanyDto companyDto){
        User user = userRepository.findByEmail(username);
        if(companyRepository.getAllByUser(user).size() < 1) {
            Company company = new Company();
            company.setUser(user);
            company.setAddress(companyDto.getAddress());
            company.setName(companyDto.getName());
            company.setEmployeeList(companyDto.getEmployeeList());
            return companyRepository.save(company);
        }else {
            throw new ResourceNotFoundException("The user already owns a company");
        }
    }

    public Boolean getCompanyByUser(long id) {
        User user = userRepository.findById(id).get();
       return companyRepository.existsCompanyByUser(user);
    }

    public CompanyDto getCompanyByUserId(long id) {
        User user = userRepository.findById(id).get();
       Company company =  companyRepository.findByUser(user);
       return mapper.mapToCompanyDto(company);
    }
}
