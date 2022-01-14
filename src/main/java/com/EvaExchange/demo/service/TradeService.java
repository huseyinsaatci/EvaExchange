package com.EvaExchange.demo.service;

import com.EvaExchange.demo.dto.ErrorResponse;
import com.EvaExchange.demo.dto.TradeRequest;
import com.EvaExchange.demo.dto.TradeResponse;
import com.EvaExchange.demo.model.Portfolio;
import com.EvaExchange.demo.model.Transaction;
import com.EvaExchange.demo.repository.PortfolioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TradeService {
    @Autowired
    UserService userService;
    @Autowired
    PortfolioRepository portfolioRepository;
    @Autowired
    ShareService shareService;
    @Autowired
    TransactionService transactionService;

    public ResponseEntity<Object> sell(TradeRequest sellDto, String shareSymbol) {
        ErrorResponse errorResponse = new ErrorResponse("Transaction could not completed");
        int shareQuantity = userService.userHasShareQuantity(sellDto.userId, shareSymbol);
        if (!shareService.shareExist(shareSymbol)) {
            errorResponse.addSubError("Share not found");
        }
        if (!userService.userExist(sellDto.userId)) {
            errorResponse.addSubError("User not found");
        } else if (shareQuantity < sellDto.quantity) {
            errorResponse.addSubError("User does not have requested quantity of share");
        } else if (shareQuantity == sellDto.quantity) {
            portfolioRepository.deletebyUserIdAndShareSymbol(sellDto.userId, shareSymbol);
        } else {
            portfolioRepository.updateItemQuantity(sellDto.userId, shareSymbol,
                    shareQuantity - sellDto.quantity);
        }
        if (errorResponse.hasError()) {
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        Transaction t = transactionService.addNewTransaction(sellDto.userId, "SELL", shareSymbol, sellDto.quantity);
        return new ResponseEntity<>(new TradeResponse(t), HttpStatus.OK);
    }

    public ResponseEntity<Object> buy(TradeRequest buyDto, String shareSymbol) {
        ErrorResponse errorResponse = new ErrorResponse("Transaction could not completed");
        if (!shareService.shareExist(shareSymbol)) {
            errorResponse.addSubError("Share not found");
        }
        if (!userService.userExist(buyDto.userId)) {
            errorResponse.addSubError("User not found");
        }
        if (!transactionService.isShareQuantityEnough(shareSymbol, buyDto.quantity)) {
            errorResponse.addSubError("There is not enough quantity of share");
        }
        if (errorResponse.hasError()) {
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        int shareQuantity = userService.userHasShareQuantity(buyDto.userId, shareSymbol);
        if (shareQuantity == 0) {
            portfolioRepository.save(new Portfolio(buyDto.userId, shareSymbol, buyDto.quantity));
        } else if (shareQuantity > 0) {
            portfolioRepository.updateItemQuantity(buyDto.userId, shareSymbol, shareQuantity + buyDto.quantity);
        }
        Transaction t = transactionService.addNewTransaction(buyDto.userId, "BUY", shareSymbol, buyDto.quantity);
        return new ResponseEntity<>(new TradeResponse(t), HttpStatus.OK);
    }
}