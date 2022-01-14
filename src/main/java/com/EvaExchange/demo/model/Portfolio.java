package com.EvaExchange.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name = "portfolio", schema = "public")
public class Portfolio {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @NonNull
    @Column(name = "user_id")
    private int userId;
    @NonNull
    @Column(name = "share_symbol")
    private String shareSymbol;
    @NonNull
    private int quantity;
}
