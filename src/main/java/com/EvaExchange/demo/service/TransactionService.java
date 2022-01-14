package com.EvaExchange.demo.service;

import java.math.BigDecimal;
import java.util.Optional;

import com.EvaExchange.demo.model.Transaction;
import com.EvaExchange.demo.repository.ShareRepository;
import com.EvaExchange.demo.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ShareRepository shareRep;

    public Transaction addNewTransaction(int userId, String type, String shareSymbol, int quantity) {
        Optional<BigDecimal> price = shareRep.findPrice(shareSymbol);
        Transaction t = new Transaction(userId, type, price.get(), shareSymbol, quantity);
        transactionRepository.save(t);
        return t;
    }

    public boolean isShareQuantityEnough(String shareSymbol, int requiredQuantity) {
        int sell = transactionRepository.totalSellTransactions(shareSymbol).orElse(0);
        int buy = transactionRepository.totalBuyTransactions(shareSymbol).orElse(0);
        int difference = sell - buy;
        if (difference >= requiredQuantity)
            return true;
        return false;
    }
}