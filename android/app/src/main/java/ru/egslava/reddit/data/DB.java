package ru.egslava.reddit.data;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by egslava@gmail.com on 20/06/2017.
 *
 * This class is intended to be an in-memory DB.
 * I use ArrayList inside instead of SortedList, because SortedList, suddenly,
 * uses just Arrays.sort() when an item is inserted. I think an insertion sort would be better here
 * but, again, see no reason to implement it now, just want to have the flexibility of choosing
 * an algorithm later.
 *
 * One more reason against of SortedList - we can pass callback only in its constructor, so
 * we need to recreate list in Adapter or make an additional abstraction layer, that I see as
 * overengineering right now.
 */

public enum DB {
    INSTANCE; // singleton :)

    private ArrayList<PostEntity> mPosts = new ArrayList<>(20);
    public List<PostEntity> posts = Collections.unmodifiableList(mPosts);

    private final Comparator<PostEntity> comparatorUpvoteDescend;

    // a girl http://farm5.static.flickr.com/4037/4289820426_31bd65d9f0_o.jpg
    DB() {
        comparatorUpvoteDescend = new Comparator<PostEntity>() {
            @Override
            public int compare(@NonNull PostEntity o1, @NonNull PostEntity o2) {
                int d = o2.numUpvotes - o1.numUpvotes;
                if (d < 0) return -1;
                if (d > 0) return 1;
                return 0;
            }
        };

        mPosts.add(new PostEntity("5 mins", "Scientists found a new way...", 15, 3, "https://ak9.picdn.net/shutterstock/videos/3374162/thumb/6.jpg"));
        mPosts.add(new PostEntity("5 mins", "Scientists found a new way...", 15, 3, "https://ak9.picdn.net/shutterstock/videos/3374162/thumb/6.jpg"));
        mPosts.add(new PostEntity("5 mins", "Scientists found a new way...", 15, 3, "https://ak9.picdn.net/shutterstock/videos/3374162/thumb/6.jpg"));
        mPosts.add(new PostEntity("5 mins", "Scientists found a new way...", 15, 3, "https://ak9.picdn.net/shutterstock/videos/3374162/thumb/6.jpg"));
        mPosts.add(new PostEntity("5 mins", "Scientists found a new way...", 15, 3, "https://ak9.picdn.net/shutterstock/videos/3374162/thumb/6.jpg"));
        mPosts.add(new PostEntity("5 mins", "Scientists found a new way...", 15, 3, "https://ak9.picdn.net/shutterstock/videos/3374162/thumb/6.jpg"));
        mPosts.add(new PostEntity("5 mins", "Scientists found a new way...", 15, 3, "https://ak9.picdn.net/shutterstock/videos/3374162/thumb/6.jpg"));
        mPosts.add(new PostEntity("5 mins", "Scientists found a new way...", 15, 3, "https://ak9.picdn.net/shutterstock/videos/3374162/thumb/6.jpg"));
        mPosts.add(new PostEntity("5 mins", "Scientists found a new way...", 15, 3, "https://ak9.picdn.net/shutterstock/videos/3374162/thumb/6.jpg"));
        mPosts.add(new PostEntity("5 mins", "Scientists found a new way...", 1500, 3, "https://ak9.picdn.net/shutterstock/videos/3374162/thumb/6.jpg"));
        mPosts.add(new PostEntity("5 mins", "Scientists found a new way...", 15, 3, "https://ak9.picdn.net/shutterstock/videos/3374162/thumb/6.jpg"));
        mPosts.add(new PostEntity("5 mins", "Scientists found a new way...", 15, 3, "https://ak9.picdn.net/shutterstock/videos/3374162/thumb/6.jpg"));
        mPosts.add(new PostEntity("5 mins", "Scientists found a new way...", 15, 3, "https://ak9.picdn.net/shutterstock/videos/3374162/thumb/6.jpg"));
        mPosts.add(new PostEntity("5 mins", "Scientists found a new way...", 15, 3, "https://ak9.picdn.net/shutterstock/videos/3374162/thumb/6.jpg"));
        mPosts.add(new PostEntity("5 mins", "Scientists found a new way...", 15, 3, "https://ak9.picdn.net/shutterstock/videos/3374162/thumb/6.jpg"));
        mPosts.add(new PostEntity("5 mins", "Scientists found a new way...", 15, 3, "https://ak9.picdn.net/shutterstock/videos/3374162/thumb/6.jpg"));
        mPosts.add(new PostEntity("5 mins", "Scientists found a new way...", 15, 3, "https://ak9.picdn.net/shutterstock/videos/3374162/thumb/6.jpg"));
        mPosts.add(new PostEntity("5 mins", "Scientists found a new way...", 15, 3, "https://ak9.picdn.net/shutterstock/videos/3374162/thumb/6.jpg"));
        mPosts.add(new PostEntity("5 mins", "Scientists found a new way...", 15, 3, "https://ak9.picdn.net/shutterstock/videos/3374162/thumb/6.jpg"));
        mPosts.add(new PostEntity("5 mins", "Scientists found a new way...", 1500, 3, "https://ak9.picdn.net/shutterstock/videos/3374162/thumb/6.jpg"));

        Collections.sort(mPosts, comparatorUpvoteDescend);
    }


    interface DbListener{
        void onDbChanged ( List<PostEntity> posts);
    }

    public void add(@NonNull PostEntity entity){
        mPosts.add(entity);
        Collections.sort(mPosts, comparatorUpvoteDescend);

        if (mListener != null){
            mListener.onDbChanged( posts );
        }
    }

    DbListener mListener = null;
    public void setListener(DbListener listener){
        if (mListener != null) throw new UnsupportedOperationException("Multiple listeners are not supported");
        mListener = listener;
    }

    public void unsetListener(){
        mListener = null;
    };


}
