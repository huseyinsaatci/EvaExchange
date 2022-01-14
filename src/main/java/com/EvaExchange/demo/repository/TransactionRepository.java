package com.EvaExchange.demo.repository;

import java.util.Optional;

import com.EvaExchange.demo.model.Transaction;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    @Query(value = "SELECT SUM(t.quantity) FROM Transaction t WHERE t.shareSymbol = ?1 AND t.type='SELL'")
    public Optional<Integer> totalSellTransactions(String shareSymbol);

    @Query(value = "SELECT SUM(t.quantity) FROM Transaction t WHERE t.shareSymbol = ?1 AND t.type='BUY'")
    public Optional<Integer> totalBuyTransactions(String shareSymbol);
}
