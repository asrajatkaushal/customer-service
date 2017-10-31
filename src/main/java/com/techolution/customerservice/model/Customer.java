package com.techolution.customerservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String firstName;

    private String lastName;

    @NotEmpty
    @Column(unique = true)
    private String email;

    @NotEmpty
    @Column(unique = true)
    private String username;

    @NotEmpty
    private String password;

}
