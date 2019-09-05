package com.christophermasse.simplecalculator.utils;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StringsToDoublesTests {

    @Test
    public void posWholeNumber(){
        assertThat(FormattingUtil.parseDouble("15"), is(15d));
    }

    @Test
    public void negWholeNumber(){
        assertThat(FormattingUtil.parseDouble("-1234"), is(-1234d));
    }

    @Test
    public void posDecimal(){
        assertThat(FormattingUtil.parseDouble("2.02935"), is(2.02935d));
    }

    @Test
    public void negDecimal() {
        assertThat(FormattingUtil.parseDouble("-4523.134444"), is(-4523.134444d));
    }

    @Test
    public void upperLimit() {
        assertThat(FormattingUtil.parseDouble("999999999999999"), is(999999999999999d));
    }
    @Test
    public void lowerLimit() {
        assertThat(FormattingUtil.parseDouble("-99999999999999"), is(-99999999999999d));
    }
}
