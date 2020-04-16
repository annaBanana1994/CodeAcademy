package com.project.finance.repository;

import com.project.finance.model.Account;
import com.project.finance.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findById (int id);

    User save (User user);

    List<User> findByFirstName(String firstName);
}
