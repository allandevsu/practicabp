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
import org.springframework.web.bind.annotation.RestController;

import com.practicabp.app.entity.Person;
import com.practicabp.app.service.PersonService;

@RestController
@RequestMapping("api/persons")
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping
	public List<Person> list(){
		List<Person> persons = StreamSupport
				.stream(personService.findAll().spliterator(), false)
				.collect(Collectors.toList());

		return persons;
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		Optional<Person> oPerson = personService.findById(id);
		
		if (!oPerson.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oPerson);
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Person person){
		return ResponseEntity.status(HttpStatus.CREATED).body(personService.save(person));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Person person, @PathVariable Long id) {
		Optional<Person> oPerson = personService.findById(id);
		
		if(!oPerson.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		if (person.getFirstName() != null){
			oPerson.get().setFirstName(person.getFirstName());
		}
		if (person.getLastName() != null){
			oPerson.get().setLastName(person.getLastName());
		}
		if (person.getDni() != null){
			oPerson.get().setDni(person.getDni());
		}
		if (person.getActive() != null){
			oPerson.get().setActive(person.getActive());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(personService.save(oPerson.get()));
	}
}
