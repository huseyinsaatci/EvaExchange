package com.EvaExchange.demo.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.EvaExchange.demo.model.Transaction;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TradeResponse {

    public TradeResponse(Transaction t) {
        userId = t.getUserId();
        type = t.getType();
        price = t.getPrice();
        shareSymbol = t.getShareSymbol();
        quantity = t.getQuantity();
        created_at = t.getCreated_at();
    }

    private int userId;

    private String type;

    private BigDecimal price;

    private String shareSymbol;

    private int quantity;

    private Date created_at;
}
