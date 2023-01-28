package pl.employer.assistance.model.mapper;

import org.mapstruct.Mapper;
import pl.employer.assistance.model.Company;
import pl.employer.assistance.model.dto.CompanyDto;

@Mapper
public interface CompanyMapper {

    CompanyDto mapToCompanyDto (Company company);

    Company mapToCompany (CompanyDto companyDto);
}
