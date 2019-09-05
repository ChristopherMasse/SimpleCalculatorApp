package com.christophermasse.simplecalculator.service;

import com.christophermasse.simplecalculator.TestingConstants;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DivisionTests {

    private ArithmeticService mService;

    @Before
    public void setup(){
        mService = new ArithmeticService();
    }

    @Test
    public void divideTwoPos(){
        Assert.assertEquals(3, mService.divide(18,6), TestingConstants.DELTA);
    }

    @Test
    public void divideNegByPos(){
        Assert.assertEquals(-3, mService.divide(-18,6), TestingConstants.DELTA);
    }

    @Test
    public void dividePosByNeg(){
        Assert.assertEquals(-3, mService.divide(18,-6), TestingConstants.DELTA);
    }

    @Test(expected = ArithmeticException.class)
    public void divideByZero(){
        mService.divide(100,0);
    }
}
