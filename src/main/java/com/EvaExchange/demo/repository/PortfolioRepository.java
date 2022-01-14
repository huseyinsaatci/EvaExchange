package com.EvaExchange.demo.repository;

import java.util.Optional;

import com.EvaExchange.demo.model.Portfolio;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PortfolioRepository extends CrudRepository<Portfolio, Integer> {
    // public Optional<Portfolio> findByUserId();

    @Query(value = "SELECT quantity FROM Portfolio WHERE userId = ?1 AND shareSymbol = ?2")
    public Optional<Integer> findShareByUserId(int userId, String shareSymbol);

    @Transactional
    @Modifying
    @Query(value = "DELETE Portfolio WHERE userId = ?1 AND shareSymbol = ?2")
    public void deletebyUserIdAndShareSymbol(int userId, String shareSymbol);

    @Transactional
    @Modifying
    @Query("UPDATE Portfolio SET quantity =?3 WHERE userId = ?1 AND shareSymbol = ?2")
    void updateItemQuantity(int userId, String shareSymbol, int quantity);
}
