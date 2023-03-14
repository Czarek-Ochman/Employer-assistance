package pl.employer.assistance.model.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import pl.employer.assistance.model.Company;
import pl.employer.assistance.model.Employee;
import pl.employer.assistance.model.dto.CompanyDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-27T13:47:14+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
public class CompanyMapperImpl implements CompanyMapper {

    @Override
    public CompanyDto mapToCompanyDto(Company company) {
        if ( company == null ) {
            return null;
        }

        CompanyDto companyDto = new CompanyDto();

        companyDto.setId( company.getId() );
        companyDto.setName( company.getName() );
        List<Employee> list = company.getEmployeeList();
        if ( list != null ) {
            companyDto.setEmployeeList( new ArrayList<Employee>( list ) );
        }
        companyDto.setAddress( company.getAddress() );
        companyDto.setUser( company.getUser() );

        return companyDto;
    }

    @Override
    public Company mapToCompany(CompanyDto companyDto) {
        if ( companyDto == null ) {
            return null;
        }

        Company company = new Company();

        company.setId( companyDto.getId() );
        company.setName( companyDto.getName() );
        List<Employee> list = companyDto.getEmployeeList();
        if ( list != null ) {
            company.setEmployeeList( new ArrayList<Employee>( list ) );
        }
        company.setAddress( companyDto.getAddress() );
        company.setUser( companyDto.getUser() );

        return company;
    }
}
