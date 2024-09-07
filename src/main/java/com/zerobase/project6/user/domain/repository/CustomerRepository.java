package com.zerobase.project6.user.domain.repository;

import com.zerobase.project6.user.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
