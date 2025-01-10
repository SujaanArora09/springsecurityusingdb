package com.training.springsecurity.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.training.springsecurity.entities.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}