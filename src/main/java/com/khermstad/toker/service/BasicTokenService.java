package com.khermstad.toker.service;

import com.khermstad.toker.repository.BasicTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicTokenService {

    @Autowired
    BasicTokenRepository basicTokenRepository;

}
