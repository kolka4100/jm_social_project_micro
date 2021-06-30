package org.javamentor.social.login.demo.dao;

import org.javamentor.social.login.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao extends JpaRepository<Account, Long> {

    Account findByEmail(String email);

}
