package ru.egslava.reddit.assertions;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Assert;

import java.util.ArrayList;

import static android.view.View.FIND_VIEWS_WITH_TEXT;


// Thanks! :)
// https://gist.github.com/chemouna/00b10369eb1d5b00401b

public final class RecyclerViewAssertions {

  public static ViewAssertion hasItemsCount(final int count) {
    return new ViewAssertion() {
      @Override public void check(View view, NoMatchingViewException e) {
        if (!(view instanceof RecyclerView)) {
          throw e;
        }
        RecyclerView rv = (RecyclerView) view;
        Assert.assertEquals(count, rv.getAdapter().getItemCount());
      }
    };
  }
}