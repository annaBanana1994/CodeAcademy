package com.project.finance.repository;

import com.project.finance.model.Account;
import com.project.finance.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    Account findById (int id);

    List <Account> findByUser(User user);

    List<Account> findByUserAndAccountType (User user, String accountType);

}
