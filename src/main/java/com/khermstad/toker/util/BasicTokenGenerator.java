package com.khermstad.toker.util;

import com.khermstad.toker.constant.BasicTokenGeneratorConstant;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class BasicTokenGenerator {

    public String generateToken(Integer tokenLength){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < tokenLength; i++){
            sb.append(getRandomDigitOrLetter());
        }
        return sb.toString();
    }

    public String getRandomLetter(){
        String letters = BasicTokenGeneratorConstant.LETTERS;
        String[] arrayOfLetters = letters.split(",");
        Random randomIndex = new Random();
        return arrayOfLetters[randomIndex.nextInt(arrayOfLetters.length)];
    }

    public String getRandomDigit(){
        Random randomDigit = new Random();
        return String.valueOf(randomDigit.nextInt(10));
    }

    public String getRandomDigitOrLetter(){
        Random random= new Random();
        return (random.nextInt(2) == 0 ? getRandomLetter() : getRandomDigit());
    }
}
