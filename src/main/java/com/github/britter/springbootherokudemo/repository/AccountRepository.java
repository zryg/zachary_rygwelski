package com.github.britter.springbootherokudemo.repository;

import com.github.britter.springbootherokudemo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
