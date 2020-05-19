package com.lifegraph.team20.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lifegraph.team20.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByemail(String email);

  Boolean existsByemail(String email);

  Boolean existsByEmail(String email);
}