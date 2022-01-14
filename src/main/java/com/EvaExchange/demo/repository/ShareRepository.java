package com.EvaExchange.demo.repository;

import java.math.BigDecimal;
import java.util.Optional;

import com.EvaExchange.demo.model.Share;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ShareRepository extends CrudRepository<Share, String> {
    @Query(value = "SELECT price  FROM Share WHERE symbol = ?1")
    public Optional<BigDecimal> findPrice(String share_symbol);
}
