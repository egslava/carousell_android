package ru.egslava.reddit.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.egslava.reddit.R;

/**
 * Created by carousell on 20/06/2017.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostViewHolder>{
    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_activity_item, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}


class PostViewHolder extends RecyclerView.ViewHolder {

    public PostViewHolder(View itemView) {
        super(itemView);
    }
}