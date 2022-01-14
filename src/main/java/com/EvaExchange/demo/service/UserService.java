package com.EvaExchange.demo.service;

import java.util.Optional;

import com.EvaExchange.demo.model.User;
import com.EvaExchange.demo.repository.PortfolioRepository;
import com.EvaExchange.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRep;
    @Autowired
    private PortfolioRepository portfolioRep;

    public void addNewUser(int id) {
        userRep.save(new User(id));
    }

    public int userHasShareQuantity(int userId, String shareSymbol) {
        Optional<Integer> quantity = portfolioRep.findShareByUserId(userId, shareSymbol);
        if (quantity.isEmpty()) {
            return 0;
        }
        return quantity.get();
    }

    public boolean userExist(int userId) {
        return userRep.findById(userId).isPresent();
    }
}