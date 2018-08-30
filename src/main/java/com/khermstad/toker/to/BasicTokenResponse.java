package com.khermstad.toker.to;

import lombok.Data;

@Data
public class BasicTokenResponse {

    private String message;
    private boolean isSuccessful;

    public BasicTokenResponse(String message, boolean isSuccessful){
        this.isSuccessful = isSuccessful;
        this.message = message;
    }
}
