package ru.egslava.reddit.ui;

import android.content.Intent;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import ru.egslava.reddit.R;
import ru.egslava.reddit.UtilsRecyclerViewActions;
import ru.egslava.reddit.assertions.ImageViewAssertions;
import ru.egslava.reddit.assertions.RecyclerViewAssertions;
import ru.egslava.reddit.data.DB;
import ru.egslava.reddit.data.PostEntity;
import ru.egslava.reddit.matchers.RecyclerViewMatchers;
import ru.egslava.reddit.ui.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by egslava@gmail.com on 20/06/2017.
 *
 * Actually, we have kind of mock data, so tests are simple as well :)
 * No need to use mock data, I assume that all our data - is a one big mock and it's ok for now
 *
 */
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mRule = new ActivityTestRule<MainActivity>(MainActivity.class, false, false);

    @Before
    public void setUp() throws Exception {

        // this is a special hack, because, it seems, Espresso doesn't wait until the previous activity has ben stopped
        // and just run the next one. So we can use this solution: https://stackoverflow.com/a/42638192
        // or just reset listeners manually in tests. Cause the bug in Espresso, I think, it's appropriate
        // just reset listeners
        DB.INSTANCE.setListener(null);
        DB.INSTANCE.clear();
    }

    @After
    public void tearDown() throws Exception {
//        DB.INSTANCE.unsetListener();
    }

    @Test
    public void showsList() {


        DB.INSTANCE.add(new PostEntity("time", "title", 1, 2, "http://eragenx.com/wp-content/uploads/2016/12/velley-of-flowers.png"));

        mRule.launchActivity(null);

        // this test, perhaps, will be passed not from the first time, because Glide need to cache the results
        // also, please, don't forget to turn on an ------Internet connection-------
        onView( allOf(ViewMatchers.withId( R.id.main_recyclerview_posts ), isDisplayed()) )
                .check(RecyclerViewAssertions.hasItemsCount(1))
                .check( matches( RecyclerViewMatchers.atPosition(0, hasDescendant(withText("time")))))
                .check( matches( RecyclerViewMatchers.atPosition(0, hasDescendant(withText("title")))))
                .check( matches( RecyclerViewMatchers.atPosition(0, hasDescendant(withText("1")))))
                .check( matches( RecyclerViewMatchers.atPosition(0, hasDescendant(withText("2")))))
//                .check( matches( RecyclerViewMatchers.atPosition(0, hasDescendant(allOf(withId(R.id.main_item_imageview),ImageViewAssertions.hasImage())) )))
                ;
    }

    @Test
    public void canUpvote(){
        DB.INSTANCE.clear();
        DB.INSTANCE.add(new PostEntity("time", "title1", 0, 0, null));
        DB.INSTANCE.add(new PostEntity("time", "title2", 0, 0, null));
        DB.INSTANCE.add(new PostEntity("time", "title3", 0, 0, null));

        mRule.launchActivity(null);

        onView(allOf( withId( R.id.main_recyclerview_posts), isDisplayed()))
                .check(RecyclerViewAssertions.hasItemsCount(3))
                .check(matches(RecyclerViewMatchers.atPosition(0, hasDescendant(withText("title1")))) );

        // clicking upvote button on the third element
        onView(allOf( withId( R.id.main_recyclerview_posts), isDisplayed()))
                .perform(RecyclerViewActions.scrollToPosition(2))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2,
                        UtilsRecyclerViewActions.clickChildViewWithId(R.id.main_item_click_upvote)));

        // should bloat up (the code is broken down on several parts, because it's very comfortable
        // to call SystemClock.sleep in between and see mistakes visually,
        onView(allOf( withId( R.id.main_recyclerview_posts), isDisplayed()))
                .check(RecyclerViewAssertions.hasItemsCount(3))
                .perform(RecyclerViewActions.scrollToPosition(0))
                .check(matches(RecyclerViewMatchers.atPosition(0, hasDescendant(withText("title3")))) )
                .check(matches(RecyclerViewMatchers.atPosition(0, hasDescendant(withText("0")))) )
                .check(matches(RecyclerViewMatchers.atPosition(0, hasDescendant(withText("1")))) );

        // we also care about the order of elements
        onView(allOf( withId( R.id.main_recyclerview_posts), isDisplayed()))
                .perform(RecyclerViewActions.scrollToPosition(1))
                .check(matches(RecyclerViewMatchers.atPosition(1, hasDescendant(withText("title1")))) )
                .check(matches(RecyclerViewMatchers.atPosition(1, hasDescendant(withText("0")))) )
                .check(matches(RecyclerViewMatchers.atPosition(1, hasDescendant(withText("0")))) );

        onView(allOf( withId( R.id.main_recyclerview_posts), isDisplayed()))
                .perform(RecyclerViewActions.scrollToPosition(2))
                .check(matches(RecyclerViewMatchers.atPosition(2, hasDescendant(withText("title2")))) )
                .check(matches(RecyclerViewMatchers.atPosition(2, hasDescendant(withText("0")))) )
                .check(matches(RecyclerViewMatchers.atPosition(2, hasDescendant(withText("0")))) );

    }
    @Test
    public void canDownvote(){
        DB.INSTANCE.clear();
        DB.INSTANCE.add(new PostEntity("time", "title1", 0, 0, null));
        DB.INSTANCE.add(new PostEntity("time", "title2", 0, 0, null));
        DB.INSTANCE.add(new PostEntity("time", "title3", 0, 0, null));

        mRule.launchActivity(null);


        onView(allOf( withId( R.id.main_recyclerview_posts), isDisplayed()))
                .check(RecyclerViewAssertions.hasItemsCount(3))
                .check(matches(RecyclerViewMatchers.atPosition(0, hasDescendant(withText("title1")))) )
                .check(matches(RecyclerViewMatchers.atPosition(1, hasDescendant(withText("title2")))) )
                .check(matches(RecyclerViewMatchers.atPosition(2, hasDescendant(withText("title3")))) )
        ;

        // clicking upvote button on the third element
        onView(allOf( withId( R.id.main_recyclerview_posts), isDisplayed()))
                .perform(RecyclerViewActions.scrollToPosition(2))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2,
                        UtilsRecyclerViewActions.clickChildViewWithId(R.id.main_item_click_downvote)));

        // should bloat up (the code is broken down on several parts, because it's very comfortable
        // to call SystemClock.sleep in between and see mistakes visually,
        onView(allOf( withId( R.id.main_recyclerview_posts), isDisplayed()))
                .check(RecyclerViewAssertions.hasItemsCount(3))
                .perform(RecyclerViewActions.scrollToPosition(0))
                .check(matches(RecyclerViewMatchers.atPosition(0, hasDescendant(withText("title1")))) )
                .check(matches(RecyclerViewMatchers.atPosition(0, hasDescendant(withText("0")))) )
                .check(matches(RecyclerViewMatchers.atPosition(0, hasDescendant(withText("0")))) );

        // we also care about the order of elements
        onView(allOf( withId( R.id.main_recyclerview_posts), isDisplayed()))
                .perform(RecyclerViewActions.scrollToPosition(1))
                .check(matches(RecyclerViewMatchers.atPosition(1, hasDescendant(withText("title2")))) )
                .check(matches(RecyclerViewMatchers.atPosition(1, hasDescendant(withText("0")))) )
                .check(matches(RecyclerViewMatchers.atPosition(1, hasDescendant(withText("0")))) );

        onView(allOf( withId( R.id.main_recyclerview_posts), isDisplayed()))
                .perform(RecyclerViewActions.scrollToPosition(2))
                .check(matches(RecyclerViewMatchers.atPosition(2, hasDescendant(withText("title3")))) )
                .check(matches(RecyclerViewMatchers.atPosition(2, hasDescendant(withText("0")))) )
                .check(matches(RecyclerViewMatchers.atPosition(2, hasDescendant(withText("1")))) );

    }

    /**
     * It's just a simple test, because the main test is in {@link AddPostActivityTest}
     */
    @Test
    public void canAdd(){
        mRule.launchActivity(null);
        onView(allOf( withId(R.id.main_fab_add), isDisplayed())).perform(ViewActions.click());

        onView( withId(R.id.add_edittext_message)).check( matches(isDisplayed()) );
    }
}