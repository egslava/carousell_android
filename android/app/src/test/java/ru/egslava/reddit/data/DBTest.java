package ru.egslava.reddit.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by egslava@gmail.com on 21/06/2017.
 */
public class DBTest {

    @Before
    public void setUp(){
        DB.INSTANCE.mPosts.clear();
    }

    @Test
    public void add() throws Exception {
        PostEntity post1 = new PostEntity("now", "New post", 0, 0, null);
        PostEntity post2 = new PostEntity("now", "New post", 1, 0, null);
        DB.INSTANCE.add(post1);
        DB.INSTANCE.add(post2);

        // assert there's no sort after an insertion
        // so the first element will be the element with 0 upvotes
        Assert.assertEquals(DB.INSTANCE.mPosts.get(0), post1);

    }

}