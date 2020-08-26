package com.neosoft.websecuritydemo.repository;

import com.neosoft.websecuritydemo.model.MyUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyUserDetailsRepository extends JpaRepository<MyUserDetails, Integer> {

    Optional<MyUserDetails> findByUsername(String username);
}
