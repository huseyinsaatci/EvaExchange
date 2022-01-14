package com.EvaExchange.demo.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name = "transaction", schema = "public")
public class Transaction {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @NonNull
    @Column(name = "user_id")
    private int userId;

    @NonNull
    private String type;

    @NonNull
    private BigDecimal price;

    @NonNull
    @Column(name = "share_symbol")
    private String shareSymbol;

    @NonNull
    private int quantity;

    @CreatedDate
    private Date created_at;

    @PrePersist
    protected void prePersist() {
        if (this.created_at == null)
            created_at = new Date();
    }
}
