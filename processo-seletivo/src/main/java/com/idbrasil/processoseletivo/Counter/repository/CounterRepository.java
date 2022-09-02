package com.idbrasil.processoseletivo.Counter.repository;

import com.idbrasil.processoseletivo.Counter.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends JpaRepository<Counter,Long> {
}
