package com.christophermasse.simplecalculator.service;

import com.christophermasse.simplecalculator.TestingConstants;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MultiplicationTests {

    private ArithmeticService mService;

    @Before
    public void setup(){
        mService = new ArithmeticService();
    }


    @Test
    public void multiplyTwoPos(){
        Assert.assertEquals(4d, mService.multiply(2d,2d), TestingConstants.DELTA);
    }

    @Test
    public void multiplyPosNeg(){
        Assert.assertEquals(-12d, mService.multiply(3d,-4d), TestingConstants.DELTA);
    }

    @Test
    public void multiplyByZero(){
        Assert.assertEquals(0d, mService.multiply(31594d,0d), TestingConstants.DELTA);
    }

    @Test
    public void multiplyDecimals(){
        Assert.assertEquals(0.0004d, mService.multiply(.02d,0.02d), TestingConstants.DELTA);
    }


}
