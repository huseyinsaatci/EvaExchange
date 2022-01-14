package com.EvaExchange.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TradeRequest {
    public int quantity, userId;
}
