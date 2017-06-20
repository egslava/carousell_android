package ru.egslava.reddit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import ru.egslava.reddit.list.PostsAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerViewPosts = (RecyclerView) findViewById(R.id.main_recyclerview_posts);
        mRecyclerViewPosts.setLayoutManager( new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerViewPosts.setAdapter(new PostsAdapter());
    }
}
