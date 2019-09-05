package com.christophermasse.simplecalculator.service;

import com.christophermasse.simplecalculator.TestingConstants;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SubtractionTests {

    private ArithmeticService mService;

    @Before
    public void setup(){
        mService = new ArithmeticService();
    }

    @Test
    public void subtractTwoPos() {
        //Given
        double a = 3;
        double b = 2;

        //When
        double actual = mService.subtract(a, b);

        //Then
        Assert.assertEquals(actual, 1, TestingConstants.DELTA);
    }

    @Test
    public void subtractNegFromPos() {
        //Given
        double a = 3;
        double b = -2;

        //When
        double actual = mService.subtract(a, b);

        //Then
        Assert.assertEquals(actual, 5, TestingConstants.DELTA);
    }

    @Test
    public void subtractPosFromNeg() {
        //Given
        double a = -3d;
        double b = 2d;

        //When
        double actual = mService.subtract(a, b);

        //Then
        Assert.assertEquals(actual, -5d, TestingConstants.DELTA);
    }

    @Test
    public void subtractNegFromNeg() {
        //Given
        double a = -100d;
        double b = -2d;

        //When
        double actual = mService.subtract(a, b);

        //Then
        Assert.assertEquals(actual, -98d, TestingConstants.DELTA);
    }

}