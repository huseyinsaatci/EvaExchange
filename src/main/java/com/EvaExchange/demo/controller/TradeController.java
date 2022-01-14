package com.EvaExchange.demo.controller;

import com.EvaExchange.demo.dto.TradeRequest;
import com.EvaExchange.demo.repository.UserRepository;
import com.EvaExchange.demo.service.TradeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/shares")
@RestController
public class TradeController {
    @Autowired
    UserRepository userRep;
    @Autowired
    TradeService tradeService;

    @PostMapping("{symbol}/sell")
    public ResponseEntity<Object> sell(@RequestBody TradeRequest sellDto, @PathVariable String symbol) {
        return tradeService.sell(sellDto, symbol);
    }

    @PostMapping("{symbol}/buy")
    public ResponseEntity<Object> buy(@RequestBody TradeRequest buyDto, @PathVariable String symbol) {
        return tradeService.buy(buyDto, symbol);
    }
}