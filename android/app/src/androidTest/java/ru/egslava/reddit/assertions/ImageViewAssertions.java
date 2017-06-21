package ru.egslava.reddit.assertions;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.ImageView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * Created by kuralay on 03/06/2017.
 */

public class ImageViewAssertions {

    public static ViewAssertion hasImageAssert(){

        return new ViewAssertion() {
            @Override
            public void check(View view, NoMatchingViewException e) {
                if ( !(view instanceof ImageView)) throw e;

                ImageView v = (ImageView) view;
                Drawable drawable = v.getDrawable();
                boolean hasImage = (drawable != null);

                if (hasImage && (drawable instanceof BitmapDrawable)) {
                    hasImage = ((BitmapDrawable)drawable).getBitmap() != null;
                }

                if (!hasImage){
                    throw e;
                }
            }
        };

    }
    public static Matcher<View> hasImage(){

        return new BoundedMatcher<View, ImageView>(ImageView.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("Should have an image");
            }

            @Override
            protected boolean matchesSafely(ImageView imageView) {
                Drawable drawable = imageView.getDrawable();
                boolean hasImage = (drawable != null);

                if (hasImage && (drawable instanceof BitmapDrawable)) {
                    hasImage = ((BitmapDrawable)drawable).getBitmap() != null;
                }

                return hasImage;
            }

//            @Override
//            public void check(View view, NoMatchingViewException e) {
//                if ( !(view instanceof ImageView)) throw e;
//
//                ImageView v = (ImageView) view;
//                Drawable drawable = v.getDrawable();
//                boolean hasImage = (drawable != null);
//
//                if (hasImage && (drawable instanceof BitmapDrawable)) {
//                    hasImage = ((BitmapDrawable)drawable).getBitmap() != null;
//                }
//
//                if (!hasImage){
//                    throw e;
//                }
//            }
        };

    }
}
