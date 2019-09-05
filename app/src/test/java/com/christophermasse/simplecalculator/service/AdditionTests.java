package com.christophermasse.simplecalculator.service;

import com.christophermasse.simplecalculator.TestingConstants;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AdditionTests {

    private ArithmeticService mService;

    @Before
    public void setup(){
        mService = new ArithmeticService();
    }


    @Test
    public void twoPos(){
        Assert.assertEquals(4, mService.add(2d,2d), TestingConstants.DELTA);
    }

    @Test
    public void twoNeg(){
        Assert.assertEquals(4, mService.add(2d,2d), TestingConstants.DELTA);
    }

    @Test
    public void twoPosDecimal(){
        Assert.assertEquals(0.00000003, mService.add(0.00000001d,0.00000002d), TestingConstants.DELTA);
    }

    @Test
    public void twoNegDecimal(){
        Assert.assertEquals(-0.00000305, mService.add(-0.00000102d,-0.00000203), TestingConstants.DELTA);
    }
}