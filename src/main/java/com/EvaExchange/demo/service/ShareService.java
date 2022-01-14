package com.EvaExchange.demo.service;

import com.EvaExchange.demo.repository.ShareRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShareService {
    @Autowired
    ShareRepository shareRepository;

    public boolean shareExist(String shareSymbol) {
        return shareRepository.findById(shareSymbol).isPresent();
    }
}
