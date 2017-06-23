package ru.egslava.reddit.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import ru.egslava.reddit.R;
import ru.egslava.reddit.data.DB;
import ru.egslava.reddit.data.PostEntity;
import ru.egslava.reddit.ui.list.PostsAdapter;

public class MainActivity extends AppCompatActivity implements PostsAdapter.ItemClickListener, View.OnClickListener {

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
    }

    @Override
    public void onClick(View view, int position, PostEntity post) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_fab_add:
                startActivity(new Intent(this, AddPostActivity.class));
                break;
        }
    }
}
