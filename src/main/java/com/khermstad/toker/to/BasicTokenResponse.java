package com.khermstad.toker.to;

import lombok.Data;

@Data
public class BasicTokenResponse {
    private String message;

    public BasicTokenResponse(String message){
        this.message = message;
    }
}
