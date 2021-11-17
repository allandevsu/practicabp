package com.practicabp.app.service;

import java.util.List;
import java.util.Optional;

import com.practicabp.app.entity.Account;

public interface AccountService {

	public Iterable<Account> findAll();

	public Optional<Account> findById(Long id);

	public Account save(Account person);

	public List<Account> findByPersonId(Long id);
}
