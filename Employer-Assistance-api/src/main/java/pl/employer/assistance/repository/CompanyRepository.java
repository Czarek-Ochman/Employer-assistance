package pl.employer.assistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.employer.assistance.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{

}