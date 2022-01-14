package com.EvaExchange.demo.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
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
@Table(name = "share", schema = "public")
public class Share {
    @NonNull
    @Id
    private String symbol;
    @NonNull
    private BigDecimal price;
    @CreatedDate
    private Date created_at;
    @LastModifiedDate
    private Date updated_at;

    @PrePersist
    protected void prePersist() {
        if (this.created_at == null)
            created_at = new Date();
        if (this.updated_at == null)
            updated_at = new Date();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updated_at = new Date();
    }

    @PreRemove
    protected void preRemove() {
        this.updated_at = new Date();
    }
}
