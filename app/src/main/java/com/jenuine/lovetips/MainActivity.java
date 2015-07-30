package com.jenuine.lovetips;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Toast;

import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity {

    private static final long ANIM_DURATION = 1000;
    private View bgViewGroup;
    private Lobster bariolBoldTextView;
    boolean isFirst = true;
    private String localPosts;
    AppDataPref appDataPref;

    @Override
    protected void onResume() {
        super.onResume();
        appDataPref=new AppDataPref(getApplicationContext());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        appDataPref=new AppDataPref(getApplicationContext());
        boolean netStatus = checkInternet();
        if(!netStatus){
            loadOops();
            return;
        }


        bariolBoldTextView = (Lobster) findViewById(R.id.mTextView);
        bariolBoldTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFirst)
                    launchActivity();
            }
        });
        bariolBoldTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                loadAbout();
                return true;
            }
        });
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setupLayout();
            setupWindowAnimations();
        }
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                launchActivity();
//            }
//        }, 1400);
//        showNotification();
        localPosts=new AppDataPref(getApplicationContext()).getPrefs("posts");
        if (localPosts.length()==0) {
            new ApiCall(new Callback() {
                @Override
                public void onSuccess(String response) {
                    Gson gson = new Gson();
                    localPosts=response;
                    appDataPref.setPrefs("posts",response);
//                  localPosts = gson.fromJson(response, Data.class);
                    TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    ConnectivityManager connec = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo mWifi = connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                    if (mWifi.isConnected()) {
                        // Do whatever
                        callImageAPI(ImageApiCall.HD);
                        return ;
                    }
                    if ((tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_HSDPA)) {
                        Log.d("Type", "3g");// for 3g HSDPA networktype will be return as
                        // per testing(real) in device with 3g enable data
                        // and speed will also matters to decide 3g network type
                        callImageAPI(ImageApiCall.LOW);
                    } else if ((tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_HSPAP)) {
                        Log.d("Type", "4g"); // /No specification for the 4g but from wiki
                        // i found(HSPAP used in 4g)
                        // http://goo.gl/bhtVT
                        callImageAPI(ImageApiCall.HD);
                    } else if ((tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_GPRS)) {
                        Log.d("Type", "GPRS");
                        callImageAPI(ImageApiCall.VLOW);
                    } else if ((tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_EDGE)) {
                        Log.d("Type", "EDGE 2g");
                        callImageAPI(ImageApiCall.VLOW);
                    }

                }

                @Override
                public void onFail(String response) {

                }
            }).execute();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    launchActivity();
                }
            }, 500);
        }

    }

    private void loadOops() {
        Fragment mFragment = new Oops();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.from_middle, R.anim.nothing, 0, R.anim.to_middle);
        ft.replace(R.id.container, mFragment).addToBackStack(null).commit();
    }

    private boolean checkInternet() {

        ConnectivityManager connec = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (mWifi.isConnected()) {
            // Do whatever
            return true;
        }
        else if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED || connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING) {
            //Do something in here when we are connected
            return true;
        } else if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED || connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED) {
            return false;
        }

        return false;
    }

    private void callImageAPI(int type) {
        new ImageApiCall(type, new Callback() {
            @Override
            public void onSuccess(String response) {
                Gson gson = new Gson();
                appDataPref.setPrefs("images",response);
//                DB.images = gson.fromJson(response, Images.class);
                launchActivity();
            }

            @Override
            public void onFail(String response) {

            }
        }).execute();

    }


    public void launchActivity() {

        Intent i = new Intent(this, ListTitles.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View sharedView = bariolBoldTextView;
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

    private void loadAbout() {
        Fragment mFragment = new AboutLovecanto();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.from_middle, R.anim.nothing, 0, R.anim.to_middle);
        ft.replace(R.id.container, mFragment).addToBackStack(null).commit();

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
                animateRevealShow(bgViewGroup);
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
                animateRevealHide(bgViewGroup);
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

    @Override
    protected void onPause() {
        super.onPause();
        isFirst = false;
    }

    public void showNotification() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                this).setAutoCancel(true)
                .setContentTitle("DJ-Android notification")
                .setSmallIcon(R.drawable.border_heart)
                .setContentText("Hello World!");

        NotificationCompat.BigPictureStyle bigPicStyle = new NotificationCompat.BigPictureStyle();
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
//                R.drawable.love36);
//        bigPicStyle.bigPicture(bitmap);
        bigPicStyle.setBigContentTitle("Dhaval Sodha Parmar");
        mBuilder.setStyle(bigPicStyle);

        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MainActivity.class);

        // The stack builder object will contain an artificial back stack
        // for
        // the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out
        // of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // mId allows you to update the notification later on.
        mNotificationManager.notify(100, mBuilder.build());
    }
}
