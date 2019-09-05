package com.christophermasse.simplecalculator.utils;

import com.christophermasse.simplecalculator.exception.ExcessiveValueException;

import org.junit.Test;

import androidx.test.filters.SmallTest;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@SmallTest
public class DoublesToStringsTests {

    @Test
    public void posWholeNumber(){
        assertThat(FormattingUtil.toString(15d), is("15"));
    }

    @Test
    public void negWholeNumber(){
        assertThat(FormattingUtil.toString(-1234d), is("-1234"));
    }

    @Test
    public void posDecimal(){
        assertThat(FormattingUtil.toString(2.02935d), is("2.02935"));
    }

    @Test
    public void negDecimal() {
        assertThat(FormattingUtil.toString(-4523.134444d), is("-4523.134444"));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test(expected = ExcessiveValueException.class)
    public void breachesUpperLimit() {
        FormattingUtil.toString(999999999999999.1d);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test(expected = ExcessiveValueException.class)
    public void breachesLowerLimit() {
        FormattingUtil.toString(-99999999999999.1d);
    }

    @Test
    public void upperLimit() {
        assertThat(FormattingUtil.toString(999999999999999d), is("999999999999999"));
    }
    @Test
    public void lowerLimit() {
        assertThat(FormattingUtil.toString(-99999999999999d), is("-99999999999999"));
    }
}