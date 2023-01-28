package pl.employer.assistance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    private String street;
    private String city;
    private String state;
    private String zipCode;

    @OneToOne(mappedBy="address")
    @JoinColumn(name = "company_id")
    @JsonIgnore
    private Company company;


    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private Employee employee;
}
