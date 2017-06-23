package ru.egslava.reddit.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import ru.egslava.reddit.R;
import ru.egslava.reddit.data.DB;
import ru.egslava.reddit.data.PostEntity;
import ru.egslava.reddit.ui.list.PostsAdapter;

public class MainActivity extends AppCompatActivity implements PostsAdapter.ItemClickListener, View.OnClickListener, DB.DbListener {

    private RecyclerView mRecyclerViewPosts;
    private PostsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerViewPosts = (RecyclerView) findViewById(R.id.main_recyclerview_posts);
        mRecyclerViewPosts.setLayoutManager( new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mAdapter = new PostsAdapter(this);
        mRecyclerViewPosts.setAdapter(mAdapter);
        mAdapter.setEntities( DB.INSTANCE.posts );


        findViewById(R.id.main_fab_add).setOnClickListener(this);
        DB.INSTANCE.setListener(this);
    }

    @Override
    protected void onDestroy() {
        DB.INSTANCE.unsetListener();
        super.onDestroy();
    }

    @Override
    public void onClick(View view, int position, PostEntity post) {
        switch (view.getId()){
            case R.id.main_item_click_upvote:{
                DB.INSTANCE.upvote(position);
                break;
            }
            case R.id.main_item_click_downvote:{
                DB.INSTANCE.downvote(position);
                break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_fab_add:
                startActivity(new Intent(this, AddPostActivity.class));
                break;
        }
    }

    @Override
    public void onDbChanged(List<PostEntity> posts) {
        mAdapter.notifyDataSetChanged();
    }
}
