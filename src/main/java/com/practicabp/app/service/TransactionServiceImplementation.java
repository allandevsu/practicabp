package com.practicabp.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practicabp.app.entity.Transaction;
import com.practicabp.app.repository.TransactionRepository;

@Service
public class TransactionServiceImplementation implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public Iterable<Transaction> findAll() {
		return transactionRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Transaction> findById(Long id) {
		return transactionRepository.findById(id);
	}

	@Override
	@Transactional
	public Transaction save(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	@Override
	@Transactional
	public Iterable<Transaction> saveAll(Iterable<Transaction> transactions) {
		return transactionRepository.saveAll(transactions);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		transactionRepository.deleteById(id);
	}

	@Override
	public List<Transaction> findAllByDateTransactionBetweenAndAccountId(Date dateTransactionStart,
			Date dateTransactionEnd, Long id) {
		return transactionRepository.findAllByDateTransactionBetweenAndAccountId(dateTransactionStart, dateTransactionEnd, id);
	}

}
