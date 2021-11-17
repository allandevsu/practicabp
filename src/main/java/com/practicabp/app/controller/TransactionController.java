package com.practicabp.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practicabp.app.entity.Transaction;
import com.practicabp.app.service.TransactionService;

@RestController
@RequestMapping("api/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@GetMapping
	public List<Transaction> list(){
		List<Transaction> transactions = StreamSupport
				.stream(transactionService.findAll().spliterator(), false)
				.collect(Collectors.toList());

		return transactions;
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		Optional<Transaction> oTransaction = transactionService.findById(id);
		
		if (!oTransaction.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oTransaction);
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Transaction transaction){
		return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.save(transaction));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if(!transactionService.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		transactionService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@RequestMapping(method=RequestMethod.GET, value="/accounts/{id}")
	public List<Transaction> list(@RequestParam String dateTransactionStart, @RequestParam String dateTransactionEnd, @PathVariable Long id) throws ParseException{
		List<Transaction> transactions = StreamSupport
				.stream(transactionService.findAllByDateTransactionBetweenAndAccountId(new SimpleDateFormat("yyyy-MM-dd").parse(dateTransactionStart), new SimpleDateFormat("yyyy-MM-dd").parse(dateTransactionEnd), id).spliterator(), false)
				.collect(Collectors.toList());

		return transactions;
	}

	@RequestMapping(method=RequestMethod.POST, value="/all")
	public ResponseEntity<?> create(@RequestBody Iterable<Transaction> transactions) {
		return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.saveAll(transactions));
	}
}
