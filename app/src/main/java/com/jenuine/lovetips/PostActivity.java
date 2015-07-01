package com.jenuine.lovetips;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


public class PostActivity extends AppCompatActivity {
    CollapsingToolbarLayout collapsingToolbar;
    Lobster titleToolbar;
    TextView mTextView;
    Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page);

        int position = getIntent().getIntExtra("position", 0);

        Data data = Data.getData();
        post = data.getArrayList().get(position);
//        Log.i("Lovecanto",post.getTitle());
//        Log.i("Lovecanto",post.getSubPostDatas().get(1).getContent());


//        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        imageView.setImageResource(ImageLibs.getImage());
//        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
//        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
//            @Override
//            public void onGenerated(Palette palette) {
//                int mutedColor = palette.getMutedColor(R.attr.colorPrimary);
//                collapsingToolbar.setContentScrimColor(mutedColor);
//                mTextView.setTextColor(mutedColor);
//            }
//        });

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        PostAdapter mAdapter = new PostAdapter(post, R.layout.cardview_header, R.layout.cardview, this);
        mRecyclerView.setAdapter(mAdapter);

//        mRecyclerView.setOnScrollListener(onScrollListener);


    }
//
//    public RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
//        boolean hideToolBar = false;
//
//        @Override
//        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//            super.onScrollStateChanged(recyclerView, newState);
//            if (hideToolBar) {
//                mTextView.setText("");
//            } else {
//                mTextView.setText(post.getTitle());
//            }
//        }
//
//        @Override
//        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//            super.onScrolled(recyclerView, dx, dy);
//            if (dy > 20) {
//                hideToolBar = true;
//            } else if (dy < -5) {
//                hideToolBar = false;
//            }
//        }
//    };
}
