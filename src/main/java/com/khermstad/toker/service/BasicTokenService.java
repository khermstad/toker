package com.khermstad.toker.service;

import com.khermstad.toker.entity.BasicToken;
import com.khermstad.toker.repository.BasicTokenRepository;
import com.khermstad.toker.to.BasicTokenResponse;
import com.khermstad.toker.util.RepositoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicTokenService {

    @Autowired
    BasicTokenRepository basicTokenRepository;

    @Autowired
    RepositoryUtil repositoryUtil;

    public BasicTokenResponse insertNewBasicToken(BasicToken basicToken){

        // first, check if application id already exists.
        if(applicationIdExists(basicToken.getApplicationId())){
            return new BasicTokenResponse("Application Id Exists");
        }

        // save token in repo
        basicTokenRepository.save(basicToken);

        return new BasicTokenResponse("Insert Successful");
    }

    public boolean applicationIdExists(String applicationId){
        return basicTokenRepository.findByApplicationId(applicationId).size() > 0;
    }
}
