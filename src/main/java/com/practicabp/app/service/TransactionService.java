package com.practicabp.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.practicabp.app.entity.Transaction;

public interface TransactionService {

	public Iterable<Transaction> findAll();

	public Optional<Transaction> findById(Long id);

	public Transaction save(Transaction transaction);

	public Iterable<Transaction> saveAll(Iterable<Transaction> transactions);

	public void deleteById(Long id);
	
	public List<Transaction> findAllByDateTransactionBetweenAndAccountId(
			@Param("dateTransactionStart") Date dateTransactionStart,
			@Param("dateTransactionEnd") Date dateTransactionEnd, Long id);
}
