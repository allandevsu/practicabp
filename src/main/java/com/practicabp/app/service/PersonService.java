package com.practicabp.app.service;

import java.util.Optional;

import com.practicabp.app.entity.Person;

public interface PersonService {

	public Iterable<Person> findAll();

	public Optional<Person> findById(Long id);

	public Person save(Person person);
}
