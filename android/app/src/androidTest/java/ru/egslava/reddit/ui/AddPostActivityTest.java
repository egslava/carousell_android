package ru.egslava.reddit.ui;

import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.hamcrest.core.AllOf;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import ru.egslava.reddit.R;
import ru.egslava.reddit.data.DB;
import ru.egslava.reddit.data.PostEntity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

/**
 * Created by egslava@gmail.com on 23/06/2017.
 */
public class AddPostActivityTest {

    @Rule
    public ActivityTestRule<AddPostActivity> mRule = new ActivityTestRule<AddPostActivity>(AddPostActivity.class);

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void indicatorWorks(){
        Resources resources = InstrumentationRegistry.getTargetContext().getResources();
        String str255 = "12345678901234567890123456789012345678901234567890" + // 50
                "12345678901234567890123456789012345678901234567890" + // 100
                "12345678901234567890123456789012345678901234567890" + // 150
                "12345678901234567890123456789012345678901234567890" + // 200
                "12345678901234567890123456789012345678901234567890" +    // 250
                "12345";

        // in the beginning - zero
        onView(withText(resources.getString(R.string.add_textview_symbols_indicator_format, 0))).check( matches( isDisplayed()) );


        // then it works
        onView( allOf(withId(R.id.add_edittext_message), isDisplayed()))
                .perform( typeText("12345") );

        onView( allOf(withId(R.id.add_textview_symbols_indicator), isDisplayed()))
                .check( matches(withText( resources.getString(R.string.add_textview_symbols_indicator_format, 5))) );

        // and we can't print more than 255 chars
        onView( allOf(withId(R.id.add_edittext_message), isDisplayed()))
                .perform( ViewActions.replaceText(""));

        onView( allOf(withId(R.id.add_edittext_message), isDisplayed()))
                .perform( typeText( str255 + "12345" ));

        onView( allOf(withId(R.id.add_edittext_message), isDisplayed()))
                .check( matches( withText(str255) ));
    }

    @Test
    public void cantAddEmptyMessage(){
        int oldSize = DB.INSTANCE.posts.size();
        onView( allOf( withId(R.id.add_edittext_message), isDisplayed() ) )
                .perform( replaceText("") );

        onView( allOf( withId(R.id.add_edittext_message), isDisplayed() ) )
                .perform( pressImeActionButton() );

        onView( allOf( withId(R.id.add_edittext_message), isDisplayed() ) )
                .check( matches(hasErrorText( InstrumentationRegistry.getTargetContext().getString(R.string.add_edittext_message_error_empty_text) )) );

        assertEquals(oldSize, DB.INSTANCE.posts.size());

    }

    @Test
    public void canAddMessage(){
        onView( allOf( withId(R.id.add_edittext_message), isDisplayed() ) )
                .perform( typeText("12345") );

        onView( allOf( withId(R.id.add_edittext_message), isDisplayed() ) )
                .perform( pressImeActionButton() );

        PostEntity post = DB.INSTANCE.posts.get(DB.INSTANCE.posts.size() - 1);
        Assert.assertEquals("12345", post.title);
        Assert.assertEquals("0", post.strDownvotes);
        Assert.assertEquals("0", post.strUpvotes);
        Assert.assertEquals(0, post.getNumUpvotes());
        Assert.assertEquals(0, post.numDownvotes);
        Assert.assertEquals(InstrumentationRegistry.getTargetContext().getString(R.string.post_picture_default), post.picture);
        Assert.assertEquals(InstrumentationRegistry.getTargetContext().getString(R.string.post_time_recently), post.time);

        // ensure, we're coming back
        // correct implementation of it should be with espresso.intents and IntentTestRule

    }

}