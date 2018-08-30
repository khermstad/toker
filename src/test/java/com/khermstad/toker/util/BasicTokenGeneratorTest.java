package com.khermstad.toker.util;

import com.khermstad.toker.constant.BasicTokenGeneratorConstant;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unittest")
@SpringBootTest
public class BasicTokenGeneratorTest {

    @Autowired
    BasicTokenGenerator basicTokenGenerator;

    private static final int tokenLength = BasicTokenGeneratorConstant.TOKEN_LENGTH;

    @Test
    public void generateTokenTest(){
        for(int i = 0; i < 100; i++){
            System.out.println(basicTokenGenerator.generateToken(tokenLength));
            Assert.assertEquals(tokenLength, basicTokenGenerator.generateToken(tokenLength).length());
        }
    }

    @Test
    public void getRandomLetterTest(){
       Assert.assertTrue(BasicTokenGeneratorConstant.LETTERS.contains(basicTokenGenerator.getRandomLetter()));
    }

    @Test
    public void getRandomDigitTest(){
        String randomDigit = basicTokenGenerator.getRandomDigit();
        Assert.assertTrue(Integer.parseInt(randomDigit) >= 0 && Integer.parseInt(randomDigit) < 10);
    }

    @Test
    public void getRandomLetterOrDigitTest(){
        String randomDigitOrLetter = basicTokenGenerator.getRandomDigitOrLetter();
        Assert.assertTrue(
                BasicTokenGeneratorConstant.LETTERS.contains(randomDigitOrLetter)
                || (Integer.parseInt(randomDigitOrLetter) >=0 && Integer.parseInt(randomDigitOrLetter) < 10)
        );
    }
}
