package com.practicabp.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.practicabp.app.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	public List<Transaction> findAllByDateTransactionBetweenAndAccountId(
			@Param("dateTransactionStart") Date dateTransactionStart,
			@Param("dateTransactionEnd") Date dateTransactionEnd, Long id);
}
