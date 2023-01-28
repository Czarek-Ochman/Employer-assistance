package pl.employer.assistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.employer.assistance.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
