package ru.egslava.reddit.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

    @Test
    public void upvote() throws Exception {
        // simple case
        PostEntity post1;
        PostEntity post2;
        PostEntity post3;

        DB.INSTANCE.mPosts.clear();
        post1 = new PostEntity("now", "1", 0, 0, null);
        post2 = new PostEntity("now", "2", 0, 0, null);
        DB.INSTANCE.add(post1);
        DB.INSTANCE.add(post2);
        DB.INSTANCE.upvote(1);


        Assert.assertEquals( post2, DB.INSTANCE.posts.get(0));

        // need to jump over several elements
        DB.INSTANCE.mPosts.clear();
        post1 = new PostEntity("now", "1", 0, 0, null);
        post2 = new PostEntity("now", "2", 0, 0, null);
        post3 = new PostEntity("now", "3", 0, 0, null);
        DB.INSTANCE.add(post1);
        DB.INSTANCE.add(post2);
        DB.INSTANCE.add(post3);
        DB.INSTANCE.upvote(2);

        // element should float but we're trying to save the order
        Assert.assertEquals( post3, DB.INSTANCE.posts.get(0));
        Assert.assertEquals( 1, post3.getNumUpvotes());
        Assert.assertEquals( post1, DB.INSTANCE.posts.get(1));
        Assert.assertEquals( post2, DB.INSTANCE.posts.get(2));
    }

}