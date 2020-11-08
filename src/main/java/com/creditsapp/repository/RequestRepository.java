package com.creditsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creditsapp.model.Request;


@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{

}
