package com.pg.backend.CsvUsingThread;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CsvRepository extends JpaRepository<CsvDataEntity,Integer> {
}
