package com.creditsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creditsapp.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
