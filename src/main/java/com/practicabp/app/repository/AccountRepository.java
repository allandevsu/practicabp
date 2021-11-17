package com.practicabp.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practicabp.app.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	public List<Account> findAllByPersonId(Long id);
}
