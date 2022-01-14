package com.EvaExchange.demo.util;

import java.math.BigDecimal;
import java.util.Random;

import com.EvaExchange.demo.dto.TradeRequest;
import com.EvaExchange.demo.model.Portfolio;
import com.EvaExchange.demo.model.Share;
import com.EvaExchange.demo.model.User;
import com.EvaExchange.demo.repository.PortfolioRepository;
import com.EvaExchange.demo.repository.ShareRepository;
import com.EvaExchange.demo.repository.UserRepository;
import com.EvaExchange.demo.service.TradeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    TradeService tradeService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PortfolioRepository portfolioRepository;
    @Autowired
    ShareRepository shareRepository;

    @Autowired
    public DataLoader(TradeService tradeService, PortfolioRepository portfolioRepository) {
        this.tradeService = tradeService;
        this.portfolioRepository = portfolioRepository;
    }

    public void run(ApplicationArguments args) {
        Random rand = new Random(192631);
        try {
            shareRepository.save(new Share("BTC", new BigDecimal(5.12)));
            shareRepository.save(new Share("ETH", new BigDecimal(2.18)));
            for (int i = 0; i < 5; i++) {
                int random_int1 = rand.nextInt(100000) + 100;
                int random_int2 = rand.nextInt(100000) + 100;
                userRepository.save(new User(i));
                portfolioRepository.save(new Portfolio(i, "BTC", random_int1));
                portfolioRepository.save(new Portfolio(i, "ETH", random_int2));
            }
            tradeService.sell(new TradeRequest(10, 0), "BTC");
            tradeService.sell(new TradeRequest(31, 1), "BTC");
            tradeService.sell(new TradeRequest(64, 3), "BTC");
            tradeService.buy(new TradeRequest(10, 2), "BTC");
            tradeService.buy(new TradeRequest(10, 4), "BTC");
            tradeService.sell(new TradeRequest(10, 3), "ETH");
            tradeService.buy(new TradeRequest(10, 4), "ETH");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}