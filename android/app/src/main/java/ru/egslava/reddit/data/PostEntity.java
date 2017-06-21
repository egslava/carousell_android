package ru.egslava.reddit.data;

/**
 * Created by egslava@gmail.com on 20/06/2017.
 */

import android.support.annotation.Nullable;

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

    @Nullable public String   picture;

    public PostEntity(String time, String title, int numUpvotes, int numDownvotes, String picture) {
        this.title = title;
        this.time = time;

        strUpvotes = String.valueOf(numUpvotes);
        strDownvotes = String.valueOf(numDownvotes);

        this.numUpvotes = numUpvotes;
        this.numDownvotes = numDownvotes;
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostEntity that = (PostEntity) o;

        if (numUpvotes != that.numUpvotes) return false;
        if (numDownvotes != that.numDownvotes) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (strUpvotes != null ? !strUpvotes.equals(that.strUpvotes) : that.strUpvotes != null)
            return false;
        if (strDownvotes != null ? !strDownvotes.equals(that.strDownvotes) : that.strDownvotes != null)
            return false;
        return picture != null ? picture.equals(that.picture) : that.picture == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + numUpvotes;
        result = 31 * result + numDownvotes;
        result = 31 * result + (strUpvotes != null ? strUpvotes.hashCode() : 0);
        result = 31 * result + (strDownvotes != null ? strDownvotes.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PostEntity{" +
                "title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", numUpvotes=" + numUpvotes +
                ", numDownvotes=" + numDownvotes +
                ", strUpvotes='" + strUpvotes + '\'' +
                ", strDownvotes='" + strDownvotes + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
