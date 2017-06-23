package ru.egslava.reddit.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    public static final String TAG = "DB";

    ArrayList<PostEntity> mPosts = new ArrayList<>(20);
    public List<PostEntity> posts = Collections.unmodifiableList(mPosts);

    private final Comparator<PostEntity> comparatorUpvoteDescend;

    // a girl http://farm5.static.flickr.com/4037/4289820426_31bd65d9f0_o.jpg
    DB() {
        comparatorUpvoteDescend = new Comparator<PostEntity>() {
            @Override
            public int compare(@NonNull PostEntity o1, @NonNull PostEntity o2) {
                int d = o2.getNumUpvotes() - o1.getNumUpvotes();
                if (d < 0) return -1;
                if (d > 0) return 1;
                return 0;
            }
        };
    }

    public void fillDemo(){




//        Fantasy wallpaper https://ae01.alicdn.com/kf/HTB1OB3GJFXXXXXJXVXXq6xXFXXXk/2016-New-137cmx69cm-Cotton-Bath-Towel-beautiful-night-sea-dolphin-moon-Printed-Large-Shower-Towel-Hotel.jpg
//
//
//        Premiere:

//
//
//        In Pictures: The Most Beautiful Natural



        mPosts.add(new PostEntity("5 mins", "Our travel plan of 2017", 1, 3, "http://eragenx.com/wp-content/uploads/2016/12/velley-of-flowers.png"));
        mPosts.add(new PostEntity("2 hours", "Meersburg Germany Lake Constance", 2, 3, "https://s-media-cache-ak0.pinimg.com/originals/47/8a/85/478a85cfbe6dc8c86475d944c02472bc.jpg"));
        mPosts.add(new PostEntity("yesterday", "Military Schooler", 3, 3, "https://militaryschooler.com/most-beautiful-military-school/randolph-macon-academy.jpg"));
        mPosts.add(new PostEntity("2 days", "AMA DABLAM THE BEAUTIFUL MOUNTAIN", 4, 3, "http://www.magicalnepal.com/blog/wp-content/uploads/bfi_thumb/Ama-Dablam-From-Kalapatthar-30dxq1gun85hz5933kyoei.jpg"));
        mPosts.add(new PostEntity("yesterday", "A beach in Brazil", 5, 3, "http://static1.businessinsider.com/image/57a0b808dd0895003a8b48ed-1190-625/14-of-the-most-beautiful-beaches-in-brazil.jpg"));
        mPosts.add(new PostEntity("5 mins", "Real Estate Agency in French Provinces", 6, 3, "https://sobarnes.com/wp-content/uploads/2016/12/you-dont-have-to-be-a-millionaire-to-visit-the-french-riviera-1000x500.jpg"));
        mPosts.add(new PostEntity("3 hours", "Imagery from Sam Raimi's OZ THE GREAT AND POWERFUL ...", 7, 3, "https://static.squarespace.com/static/51b3dc8ee4b051b96ceb10de/51ce6099e4b0d911b4489b79/51ce6178e4b0d911b4495465/1342552342099/1000w/oz-great-powerful-7162012.jpeg"));
        mPosts.add(new PostEntity("An hour", "World's most scenic airport landings", 8, 3, "http://static3.businessinsider.com/image/5911ff1cdd0895f7598b466c-1190-625/the-10-most-beautiful-airport-landings-in-the-world.jpg"));
        mPosts.add(new PostEntity("2 hours", "Just nature...", 9, 3, "https://coresites-cdn.factorymedia.com/twc/wp-content/uploads/2015/06/uk-trails-best-natural-mountain-biking-trails-6.jpg"));
        mPosts.add(new PostEntity("A day", "Agriculture Photography by Todd Klassy", 10, 3, "https://static1.squarespace.com/static/50631261e4b0e9530e2c53a7/53e99182e4b06e563931739c/575b1b1307eaa0338ce464aa/1493438255527/Beautiful-Montana-Barns.jpg"));
        mPosts.add(new PostEntity("5 mins", "Drawings Of Nature", 11, 3, "http://img.cheapsoccercleatsale.com/images/g03.a.alicdn.com/kf/HTB1qA7LHVXXXXaNXFXXq6xXFXXXE/Harmony-with-font-b-nature-b-font-DIY-painting-diy-font-b-draw-b-font-resin.jpg"));
        mPosts.add(new PostEntity("5 mins", "Cheap Village", 12, 3, "https://ae01.alicdn.com/kf/HTB1hN0gIXXXXXcoXXXXq6xXFXXX9/Free-shipping-font-b-beautiful-b-font-scenery-wall-arts-tapestries-classical-design-peaceful-font-b.jpg"));
        mPosts.add(new PostEntity("3 months", "Fountaineer debut", 13, 3, "http://tonedeaf.com.au/wp-content/uploads/2017/06/fountaineer.png"));
        mPosts.add(new PostEntity("3 hours", "Top 50 Most Beautiful Flowers in the World ", 14, 3, "http://a2zpost.com/wp-content/uploads/2017/01/MBRP-21.jpg"));
        mPosts.add(new PostEntity("2 days", "5th International Symposium on Highway Geometric Design", 15, 3, "https://static1.squarespace.com/static/51cc8d46e4b0b242fc8d0f33/t/552f168de4b0cea11df1ccf7/1429149326076/Beautiful_BC.jpg?format=1500w"));
        mPosts.add(new PostEntity("5 mins", "10 Beautiful Islands That No One Lives...", 16, 3, "http://www.lolwot.com/wp-content/uploads/2016/01/10-beautiful-islands-that-no-one-lives-on-4.jpg"));
        mPosts.add(new PostEntity("yesterday", "DIY Hairstyle For Wedding", 17, 3, "http://4.bp.blogspot.com/-CuHFKA_jwPw/VorrUtk_2_I/AAAAAAAACow/YpUlvYlDDOk/s1600/Beautiful%2BDIY%2BHairstyle%2BFor%2BWedding%2BAnd%2BParty.jpg"));
        mPosts.add(new PostEntity("2 days", "Be Boldly Expo Tickets", 18, 3, "https://img.evbuc.com/https%3A%2F%2Fcdn.evbuc.com%2Fimages%2F29895199%2F139578051579%2F1%2Foriginal.jpg?w=1000&rect=109%2C0%2C1484%2C742&s=57633700dd46db9f6066655b827dd4a9"));
        mPosts.add(new PostEntity("5 mins", "Hanabi Japanese fireworks", 19, 3, "http://www.j-r-s.com/wp-content/uploads/cache/images/remote/i1-wp-com/134463960906413229088__DSC0228-629054971.png"));
        mPosts.add(new PostEntity("2 years", "Bright apartment in New Jersey  ", 20, 3, "http://homeklondike.org/wp-content/uploads/2015/08/1-beautiful-interior-in-shades-of-yellow.jpg"));

        Collections.sort(mPosts, comparatorUpvoteDescend);
    }


    public interface DbListener{
        void onDbChanged ( List<PostEntity> posts);
    }

    public void add(@NonNull PostEntity entity){
        mPosts.add(entity);
        if (entity.getNumUpvotes() != 0){
            Log.w(TAG, "add: it seems entity.numUpvotes has a value, different from 0, " + entity);
        }
//        Collections.sort(mPosts, comparatorUpvoteDescend);

        if (mListener != null){
            mListener.onDbChanged( posts );
        }
    }

    /** in real life it should be long, but in our case int is more than enough */
    public void upvote(int position) {
        mPosts.get(position).setNumUpvotes(mPosts.get(position).getNumUpvotes() + 1);
        while (position > 0 ){
            if (mPosts.get(position).getNumUpvotes() <= mPosts.get(position - 1).getNumUpvotes()) break;
            Collections.swap(mPosts, position, position - 1);
            position --;
        }

        if (mListener != null){
            mListener.onDbChanged( posts );
        }
    }
    /** in real life it should be long, but in our case int is more than enough */
    public void downvote(int position) {
        PostEntity post = mPosts.get(position);
        post.numDownvotes++;
        post.strDownvotes = String.valueOf(post.numDownvotes);

        if (mListener != null){
            mListener.onDbChanged( posts );
        }
    }

    public void clear(){
        mPosts.clear();
        if (mListener != null){
            mListener.onDbChanged( posts );
        }
    }

    DbListener mListener = null;

    /** @param listener a listener or null if we want to unsubscribe*/
    public void setListener(@Nullable DbListener listener){
//        new Exception().printStackTrace();
//        if (mListener != null) throw new UnsupportedOperationException("Multiple listeners are not supported");
        mListener = listener;
    }

    public void unsetListener(DbListener listener){
        if (mListener == listener){
            mListener = null;
        }
    };


}
