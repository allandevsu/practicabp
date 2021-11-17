package com.practicabp.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practicabp.app.entity.Person;
import com.practicabp.app.repository.PersonRepository;

@Service
public class PersonServiceImplementation implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Person> findAll() {
		return personRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Person> findById(Long id) {
		return personRepository.findById(id);
	}

	@Override
	@Transactional
	public Person save(Person person) {
		return personRepository.save(person);
	}
}
