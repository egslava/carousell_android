package ru.egslava.reddit.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.List;

import ru.egslava.reddit.R;
import ru.egslava.reddit.data.PostEntity;

/**
 * Created by egslava@gmail.com on 20/06/2017.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostViewHolder>{

    List<PostEntity> mEntities;
    ItemClickListener mItemClickListener;

    public PostsAdapter(ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface ItemClickListener {
        void onClick(View view, int position, PostEntity post);
    }

    public void setEntities(List<PostEntity> entities) {
        this.mEntities = entities;
        notifyDataSetChanged();
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_activity_item, parent, false);
        return new PostViewHolder(view, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.bind(position, mEntities.get(position));
    }


    @Override
    public int getItemCount() {
        return mEntities.size();
    }
}


class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final PostsAdapter.ItemClickListener mItemClickListener;
    private final ImageView mImageView;
    private final TextView mTextViewDescription;
    private final TextView mTextViewDownvotes;
    private final TextView mTextViewUpvotes;
    private final TextView mTextViewTitle;
    private int mPosition;
    private PostEntity mPost;

    public PostViewHolder(View itemView, PostsAdapter.ItemClickListener itemClickListener) {
        super(itemView);
        mItemClickListener = itemClickListener;


        itemView.findViewById(R.id.main_item_click_downvote).setOnClickListener(this);
        itemView.findViewById(R.id.main_item_click_upvote).setOnClickListener(this);
        mImageView = (ImageView) itemView.findViewById(R.id.main_item_imageview);
        mTextViewDescription    = (TextView)   itemView.findViewById(R.id.main_item_textview_description);
        mTextViewDownvotes      = (TextView)   itemView.findViewById(R.id.main_item_textview_downvotes);
        mTextViewUpvotes        = (TextView)   itemView.findViewById(R.id.main_item_textview_upvotes);
        mTextViewTitle          = (TextView)   itemView.findViewById(R.id.main_item_textview_title);
    }

    public void bind( int position, PostEntity post ){
        mPosition = position;
        mPost = post;

        if (post.picture != null){
            Glide.with(itemView.getContext()).load(post.picture).into(mImageView);
        } else {
            Glide.with(itemView.getContext()).clear(mImageView);

        }
        mTextViewDescription.setText (post.time);
        mTextViewTitle.setText( post.title );
        mTextViewDownvotes.setText( post.strDownvotes );
        mTextViewUpvotes.setText( post.strUpvotes );
    }

    @Override
    public void onClick(View v) {
        if (mItemClickListener != null){
            mItemClickListener.onClick(v, mPosition, mPost);
        }
    }
}