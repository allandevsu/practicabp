package com.practicabp.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practicabp.app.entity.Account;
import com.practicabp.app.repository.AccountRepository;

@Service
public class AccountServiceImplementation implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Account> findAll() {
		return accountRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Account> findById(Long id) {
		return accountRepository.findById(id);
	}

	@Override
	@Transactional
	public Account save(Account account) {
		return accountRepository.save(account);
	}

	@Override
	@Transactional
	public List<Account> findByPersonId(Long id){
		return accountRepository.findAllByPersonId(id);
	}
}
