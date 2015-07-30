package com.jenuine.lovetips;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.jenuine.lovetips.itemanimator.ScaleInOutItemAnimator;
import com.squareup.picasso.Picasso;

public class ListTitles extends AppCompatActivity {

    private RecyclerView recyclerView;
    //    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final long ANIM_DURATION = 1000;
    private View bgViewGroup;
    Data data;
    ImageView imageView;
    private Images images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);

        // Actionbar
//        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        imageView = (ImageView) findViewById(R.id.backdrop);
//        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
//        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
//            @Override
//            public void onGenerated(Palette palette) {
//                int mutedColor = palette.getMutedColor(R.attr.colorPrimary);
//                collapsingToolbar.setContentScrimColor(mutedColor);
//
//            }
//        });

        // Link the views
        recyclerView = (RecyclerView) findViewById(R.id.simpleRecyclerView);

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // Use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setupLayout();
            setupWindowAnimations();
        }
        String imagedata=new AppDataPref(getApplicationContext()).getPrefs("images");
        Picasso.with(getApplicationContext()).load(ImageLibs.getImage(imagedata)).into(imageView, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                findViewById(R.id.progress_circular).setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });
        String postsString = new AppDataPref(getApplicationContext()).getPrefs("posts");
        data = new Gson().fromJson(postsString, Data.class);
        recyclerView.setItemAnimator(new ScaleInOutItemAnimator(recyclerView));
        ListTitlesAdapter mSimpleRecyclerViewAdapter = new ListTitlesAdapter(data);
        recyclerView.setAdapter(mSimpleRecyclerViewAdapter);
        mSimpleRecyclerViewAdapter.setOnClickListener(new ListTitlesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(getApplicationContext(), position + "", Toast.LENGTH_LONG).show();
                launch(position);
            }
        });
    }

    public void launch(int position) {
        Intent i = new Intent(getApplicationContext(), PostActivity.class);
        i.putExtra("position", position);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View sharedView = recyclerView;
//        String transitionName = getString(R.string.square_orange_name);
//        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this, sharedView, transitionName);
//        startActivity(i, transitionActivityOptions.toBundle());
            String transitionName = getString(R.string.square_orange_name);
            ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this, sharedView, transitionName);
            startActivity(i, transitionActivityOptions.toBundle());
        } else {
            startActivity(i);
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


}
