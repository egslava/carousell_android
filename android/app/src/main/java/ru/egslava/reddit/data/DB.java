package ru.egslava.reddit.data;

import android.support.annotation.NonNull;
import android.util.Log;

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

    public static final String TAG = "DB";

    ArrayList<PostEntity> mPosts = new ArrayList<>(20);
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
    }

    public void fillDemo(){
        mPosts.add(new PostEntity("5 mins", "Smiling girl in white blouse", 15, 3, "http://thedailynewnation.com/library/1494866166_1.jpg"));
        mPosts.add(new PostEntity("2 hours", "Blond girl with grey eyes", 15, 3, "https://wallpaperscraft.com/image/girl_face_blonde_hair_wind_hair_29111_2560x1600.jpg"));
        mPosts.add(new PostEntity("yesterday", "Happy girl in pink hat", 15, 3, "http://www.newhdwallpaper.in/wp-content/uploads/2014/09/Pretty-happy-girl-stylish-look.jpg"));
        mPosts.add(new PostEntity("2 days", "The girl on a beach", 15, 3, "http://7bna.net/images/wallpapers-girl/wallpapers-girl-17.jpg"));
        mPosts.add(new PostEntity("yesterday", "Tanned girl with green eyes", 15, 3, "http://hanksrepublic.com/wp-content/uploads/2017/05/ravishing-wallpaper-woman-hd.jpg"));
        mPosts.add(new PostEntity("5 mins", "Brunette girl with grey eyes", 15, 3, "http://bestscreenwallpaper.pro/wp-content/uploads/2013/10/megan-fox-megan-fox-wallpapers-HD-magan-fox-images-megan-fox-movies-9.jpg"));
        mPosts.add(new PostEntity("3 hours", "The girl in white posing", 15, 3, "https://images4.alphacoders.com/163/163518.jpg"));
        mPosts.add(new PostEntity("An hour", "Heart in hands", 15, 3, "http://www.sunhome.ru/i/wallpapers/39/devushki-nimfomanki.orig.jpg"));
        mPosts.add(new PostEntity("2 hours", "Indian girl with a card", 15, 3, "https://cdn.cloudpix.co/images/beautiful-women/beautiful-women-hd-wallpapers-beautiful-women-hd-wallpapers-for-mobile-free-download-desktop-wallpaper-facebook-of-flowers-nature-baby-wallpaper-b109f8df0bcbe5b42762303d698f8088-large-1164673.jpg"));
        mPosts.add(new PostEntity("A day", "Black hair girl in white dress", 15, 3, "https://www.highreshdwallpapers.com/wp-content/uploads/2012/08/Beautiful-Woman-Wallpaper.jpg"));
        mPosts.add(new PostEntity("5 mins", "Model with wind in hair", 15, 3, "http://goldwallpapers.com/uploads/posts/pretty-women-wallpaper/pretty_women_wallpaper_005.jpg"));
        mPosts.add(new PostEntity("5 mins", "Young teenage girl", 15, 3, "https://s-media-cache-ak0.pinimg.com/originals/7d/9d/84/7d9d84d283175a3d0eb3b73db7bb2d1b.jpg"));
        mPosts.add(new PostEntity("3 months", "Hairstyle with white flower", 1500, 3, "http://www.incrediblesnaps.com/wp-content/uploads/2015/09/beautiful-girl-with-flowers-7.jpg"));
        mPosts.add(new PostEntity("3 hours", "Girl with a bracelet", 15, 3, "https://wallpaperscraft.com/image/blonde_hair_makeup_face_model_96124_1920x1080.jpg"));
        mPosts.add(new PostEntity("2 days", "Morning of a model", 15, 3, "https://s-media-cache-ak0.pinimg.com/originals/e5/ec/fe/e5ecfe99b6ee0a3baccc9ef8689038a6.jpg"));
        mPosts.add(new PostEntity("5 mins", "Girl next door in green shirt", 15, 3, "http://cdn2.hd-background.com/wp-content/uploads/2016/04/Awesome-girl-hd-wallpapers-hd.jpg"));
        mPosts.add(new PostEntity("yesterday", "Red hair girl", 15, 3, "http://7bna.net/images/wallpapers-girl/wallpapers-girl-9.jpg"));
        mPosts.add(new PostEntity("2 days", "Ginger girl", 15, 3, "http://www.atozpictures.com/admin/uploads/2016/06/emma-stone-hd-desktop-wallpaper.jpg"));
        mPosts.add(new PostEntity("5 mins", "Girl near the window", 15, 3, "https://i.ytimg.com/vi/0xe4H666drk/maxresdefault.jpg"));
        mPosts.add(new PostEntity("2 years", "Black girl", 1500, 3, "http://top101news.com/wp-content/uploads/2016/06/Gabrielle-Union-USA-most-beautiful-black-women-2017.jpg"));

        Collections.sort(mPosts, comparatorUpvoteDescend);
    }


    interface DbListener{
        void onDbChanged ( List<PostEntity> posts);
    }

    public void add(@NonNull PostEntity entity){
        mPosts.add(entity);
        if (entity.numUpvotes != 0){
            Log.w(TAG, "add: it seems entity.numUpvotes has a value, different from 0, " + entity);
        }
//        Collections.sort(mPosts, comparatorUpvoteDescend);

        if (mListener != null){
            mListener.onDbChanged( posts );
        }
    }

    /** in real life it should be long, but in our case int is more than enough */
    public void upvote(int position) {
        mPosts.get(position).numUpvotes++;
        while (position > 0 ){
            if (mPosts.get(position).numUpvotes <= mPosts.get(position - 1).numUpvotes) break;
            Collections.swap(mPosts, position, position - 1);
            position --;
        }

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
