package com.zerobase.project6.user.domain.repository;

import com.zerobase.project6.user.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByLoginId(String loginId);

    Optional<Customer> findByCustomerToken(String customerToken);

}
