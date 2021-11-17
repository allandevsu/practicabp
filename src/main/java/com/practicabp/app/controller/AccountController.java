package com.practicabp.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.practicabp.app.entity.Account;
import com.practicabp.app.service.AccountService;

@RestController
@RequestMapping("api/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping
	public List<Account> list(){
		List<Account> accounts = StreamSupport
				.stream(accountService.findAll().spliterator(), false)
				.collect(Collectors.toList());

		return accounts;
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		Optional<Account> oAccount = accountService.findById(id);
		
		if (!oAccount.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oAccount);
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Account account){
		return ResponseEntity.status(HttpStatus.CREATED).body(accountService.save(account));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Account account, @PathVariable Long id) {
		Optional<Account> oAccount = accountService.findById(id);
		
		if(!oAccount.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		if (account.getEmail() != null){
			oAccount.get().setEmail(account.getEmail());
		}
		if (account.getPassword() != null){
			oAccount.get().setPassword(account.getPassword());
		}
		if (account.getPerson() != null){
			oAccount.get().setPerson(account.getPerson());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(accountService.save(oAccount.get()));
	}

	@RequestMapping(method=RequestMethod.GET, value="/persons/{id}")
	public List<Account> list(@PathVariable Long id){
		List<Account> accounts = StreamSupport
				.stream(accountService.findByPersonId(id).spliterator(), false)
				.collect(Collectors.toList());

		return accounts;
	}
}
