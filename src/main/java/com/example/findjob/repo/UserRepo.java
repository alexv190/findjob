package com.example.findjob.repo;

import com.example.findjob.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User findByActivationCode(String activationCode);
}
