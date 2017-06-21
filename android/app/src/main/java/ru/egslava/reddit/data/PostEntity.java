package ru.egslava.reddit.data;

/**
 * Created by egslava@gmail.com on 20/06/2017.
 */

/**
 * So, I assume, that we receive data from some API, then, we transform it in some inner form
 * that is comfortable for consuming by RecyclerView's Adapter.
 *
 * This class - is that _inner form_. So that's why time is already a String
 */
public class PostEntity {
    public String   title;
    public String   time;       // I think we should't format time string everytime a view is requested

    public int      numUpvotes;
    public int      numDownvotes;

    /** @see #numUpvotes */
    public String   strUpvotes;

    /** @see #numDownvotes */
    public String   strDownvotes;

    public String   picture;

    public PostEntity(String time, String title, int numUpvotes, int numDownvotes, String picture) {
        this.title = title;
        this.time = time;

        strUpvotes = String.valueOf(numUpvotes);
        strDownvotes = String.valueOf(numDownvotes);

        this.numUpvotes = numUpvotes;
        this.numDownvotes = numDownvotes;
        this.picture = picture;
    }
}
