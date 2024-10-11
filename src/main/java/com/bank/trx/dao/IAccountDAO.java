package com.bank.trx.dao;

import com.bank.trx.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountDAO extends JpaRepository<Account, Long> {}