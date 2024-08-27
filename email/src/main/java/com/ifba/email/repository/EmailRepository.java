package com.ifba.email.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifba.email.models.Email;

public interface EmailRepository extends JpaRepository<Email, Long>{

}
