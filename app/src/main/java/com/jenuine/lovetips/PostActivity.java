package com.jenuine.lovetips;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;


public class PostActivity extends AppCompatActivity {
    CollapsingToolbarLayout collapsingToolbar;
    Lobster titleToolbar;
    TextView mTextView;
    Post post;
    private static final long ANIM_DURATION = 1000;
    private View bgViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page);

        int position = getIntent().getIntExtra("position", 0);

        String postsString = new AppDataPref(getApplicationContext()).getPrefs("posts");
        Data data = new Gson().fromJson(postsString, Data.class);
        post = data.getArrayList().get(position);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView imageView = (ImageView) findViewById(R.id.backdrop);
//        imageView.setImageResource(ImageLibs.getImage());
//        Log.i("Lovecanto",post.getTitle());
//        Log.i("Lovecanto",post.getSubPostDatas().get(0).getContent());
//        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
//        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
//            @Override
//            public void onGenerated(Palette palette) {
//                int mutedColor = palette.getMutedColor(R.attr.colorPrimary);
//                collapsingToolbar.setContentScrimColor(mutedColor);
//                mTextView.setTextColor(mutedColor);
//            }
//        });
        String imagedata=new AppDataPref(getApplicationContext()).getPrefs("images");
        String url = ImageLibs.getImage(imagedata);
//        Log.v("Lovecanto",url);
//        Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();
        Picasso picasso = Picasso.with(getApplicationContext());
        picasso.load(url).into(imageView, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                findViewById(R.id.progress_circular).setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        PostAdapter mAdapter = new PostAdapter(post, R.layout.cardview_header, R.layout.cardview, this);
        mRecyclerView.setAdapter(mAdapter);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setupLayout();
            setupWindowAnimations();
        }

    }

    private void setupLayout() {
        bgViewGroup = findViewById(R.id.backgroundViewGroup);
    }

    private void setupWindowAnimations() {
        setupEnterAnimations();
        setupExitAnimations();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupEnterAnimations() {
        Transition enterTransition = getWindow().getSharedElementEnterTransition();
        enterTransition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                animateRevealHide(bgViewGroup);
            }

            @Override
            public void onTransitionCancel(Transition transition) {
            }

            @Override
            public void onTransitionPause(Transition transition) {
            }

            @Override
            public void onTransitionResume(Transition transition) {
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupExitAnimations() {
        Transition sharedElementReturnTransition = getWindow().getSharedElementReturnTransition();
        sharedElementReturnTransition.setStartDelay(ANIM_DURATION);


        Transition returnTransition = getWindow().getReturnTransition();
        returnTransition.setDuration(ANIM_DURATION);
        returnTransition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                animateRevealShow(bgViewGroup);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
            }

            @Override
            public void onTransitionCancel(Transition transition) {
            }

            @Override
            public void onTransitionPause(Transition transition) {
            }

            @Override
            public void onTransitionResume(Transition transition) {
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void animateRevealShow(View viewRoot) {
        int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
        int cy = (viewRoot.getTop() + viewRoot.getBottom()) / 2;
        int finalRadius = Math.max(viewRoot.getWidth(), viewRoot.getHeight());

        Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, cx, cy, 0, finalRadius);
        viewRoot.setVisibility(View.VISIBLE);
        anim.setDuration(ANIM_DURATION);
        anim.start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void animateRevealHide(final View viewRoot) {
        int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
        int cy = (viewRoot.getTop() + viewRoot.getBottom()) / 2;
        int initialRadius = viewRoot.getWidth();

        Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, cx, cy, initialRadius, 0);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                viewRoot.setVisibility(View.INVISIBLE);
            }
        });
        anim.setDuration(ANIM_DURATION);
        anim.start();
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
