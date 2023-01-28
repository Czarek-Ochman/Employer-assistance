package pl.employer.assistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.employer.assistance.model.Company;
import pl.employer.assistance.model.User;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Boolean existsCompanyByUser(User user);

    Company findByUser(User user);

    List<Company> getAllByUser(User user);

}
