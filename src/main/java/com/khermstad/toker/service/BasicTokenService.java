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
            return new BasicTokenResponse("Application Id Exists", false);
        }

        // save token in repo
        basicTokenRepository.save(basicToken);

        return new BasicTokenResponse("Insert Successful", true);
    }

    public BasicTokenResponse enableTokenForApplicationId(String applicationId){
        BasicToken requestedApplication =  basicTokenRepository.findByApplicationId(applicationId);
        if (null != requestedApplication){
            requestedApplication.setEnabled(true);
        }
        else{
            return new BasicTokenResponse("Application Id Not Found", false);
        }
        return updateTokenIsEnabled(requestedApplication);
    }

    public BasicTokenResponse disableTokenForApplicationId(String applicationId){
        BasicToken requestedApplication =  basicTokenRepository.findByApplicationId(applicationId);
        if (null != requestedApplication){
            requestedApplication.setEnabled(false);
        }
        else{
            return new BasicTokenResponse("Application Id Not Found", false);
        }
        return updateTokenIsEnabled(requestedApplication);
    }

    public BasicTokenResponse updateTokenIsEnabled(BasicToken basicToken){
        basicTokenRepository.save(basicToken);
        if(basicToken.isEnabled()){
            return new BasicTokenResponse("Token Enabled", true);
        }
        return new BasicTokenResponse("Token Disabled", true);
    }

    public boolean applicationIdExists(String applicationId){
        return null != basicTokenRepository.findByApplicationId(applicationId);
    }
}
