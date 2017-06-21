package ru.egslava.reddit;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.hamcrest.core.AllOf;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import ru.egslava.reddit.assertions.ImageViewAssertions;
import ru.egslava.reddit.assertions.RecyclerViewAssertions;
import ru.egslava.reddit.matchers.RecyclerViewMatchers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

/**
 * Created by egslava@gmail.com on 20/06/2017.
 *
 * Actually, we have kind of mock data, so tests are simple as well :)
 * No need to use mock data, I assume that all our data - is a one big mock and it's ok for now
 *
 */
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void showsList() {

        // this test, perhaps, will be passed not from the first time, because Glide need to cache the results
        // also, please, don't forget to turn on an Internet connection
        onView( allOf(withId( R.id.main_recyclerview_posts ), isDisplayed()) )
                .check(RecyclerViewAssertions.hasItemsCount(20))
                .check( matches( RecyclerViewMatchers.atPosition(0, hasDescendant(withText("5 mins")))))
                .check( matches( RecyclerViewMatchers.atPosition(0, hasDescendant(withText("Scientists found a new way...")))))
                .check( matches( RecyclerViewMatchers.atPosition(0, hasDescendant(withText("1500")))))
                .check( matches( RecyclerViewMatchers.atPosition(0, hasDescendant(withText("3")))))
                .check( matches( RecyclerViewMatchers.atPosition(0, hasDescendant(allOf(withId(R.id.main_item_imageview),ImageViewAssertions.hasImage())) )))
                ;
    }

}