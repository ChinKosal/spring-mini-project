package com.mybatis.springminiproject.service.serviceImpl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.mybatis.springminiproject.repository.OptsRepository;
import com.mybatis.springminiproject.service.OptsService;
import org.springframework.stereotype.Service;
@Service
public class OptsServiceImp implements OptsService {
    private OptsRepository optsRepository;

    public OptsServiceImp(OptsRepository optsRepository) {
        this.optsRepository = optsRepository;
    }
    @Override
    public void insertOpt(String optCode, Boolean verify, Integer userId) {
        LocalDateTime issued = LocalDateTime.now();
        LocalDateTime expiration = issued.plus(1, ChronoUnit.MINUTES);
        optsRepository.insertOpt(optCode,issued, expiration, verify, userId);
    }
}
