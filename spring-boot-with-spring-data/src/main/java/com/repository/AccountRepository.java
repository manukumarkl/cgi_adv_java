package com.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bean.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByAccno(Long accno);
}