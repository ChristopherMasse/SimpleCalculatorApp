package com.christophermasse.simplecalculator.view.activity;

import com.christophermasse.simplecalculator.Calculator;
import com.christophermasse.simplecalculator.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;


import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
@MediumTest
@RunWith(AndroidJUnit4.class)
public class ActivityTest {

    @Rule
    public ActivityTestRule<CalculatorActivity> rule = new ActivityTestRule<>(CalculatorActivity.class);

    @Test
    public void checkBasicAddition(){
        CalculatorActivity activity = rule.getActivity();
        onView(withId(R.id.btn_7)).perform(click());
        onView(withId(R.id.btn_addition)).perform(click());
        onView(withId(R.id.btn_7)).perform(click());
        onView(withId(R.id.btn_calculate)).perform(click());

        double result = activity.getCurrentVal();

        assertThat(result, is(14d));
        onView(withId(R.id.text_output)).check(matches(withText("14")));
    }

    @Test
    public void initialViewIsCorrect(){
        onView(withId(R.id.text_output)).check(matches(isDisplayed()));
        //Make sure the initial display value is 0
        onView(withId(R.id.text_output)).check(matches(withText("0")));
    }


    @Test
    public void titleIsCorrect(){
        String title = rule.getActivity().getTitle().toString().trim();
        assertThat(title, is("SimpleCalculator"));
    }


    @Test
    public void checkChainedOperation() {
        CalculatorActivity activity = rule.getActivity();
        onView(withId(R.id.btn_1)).perform(click());
        onView(withId(R.id.btn_0)).perform(click());
        onView(withId(R.id.btn_sign)).perform(click());

        // Check that -10 has been input
        onView(withId(R.id.text_output)).check(matches(withText("-10")));


        onView(withId(R.id.btn_subtraction)).perform(click());
        onView(withId(R.id.btn_2)).perform(click());


        // Check that 2 has been input
        onView(withId(R.id.text_output)).check(matches(withText("2")));

        onView(withId(R.id.btn_calculate)).perform(click());

        // Answer should be -12
        onView(withId(R.id.text_output)).check(matches(withText("-12")));
        double result = activity.getCurrentVal();
        assertThat(result, is(-12d));

        // Perform another operation
        onView(withId(R.id.btn_division)).perform(click());
        onView(withId(R.id.btn_division)).perform(click()); // redundant operator, should do nothing

        // Divide by -2
        onView(withId(R.id.btn_2)).perform(click());
        onView(withId(R.id.btn_sign)).perform(click());

        // Check that -2 has been input
        onView(withId(R.id.text_output)).check(matches(withText("-2")));

        onView(withId(R.id.btn_calculate)).perform(click());

        onView(withId(R.id.text_output)).check(matches(withText("6")));
        result = activity.getCurrentVal();
        assertThat(result, is(6d));
    }

}