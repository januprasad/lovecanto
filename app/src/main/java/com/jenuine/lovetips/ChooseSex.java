package com.jenuine.lovetips;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseSex extends Fragment {


    public ChooseSex() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_sex, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ImageView boy=(ImageView)view.findViewById(R.id.boyIcon);
        final ImageView girl=(ImageView)view.findViewById(R.id.girlIcon);
        final AppDataPref appDataPref=new AppDataPref(this.getActivity());
        boy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appDataPref.setPrefs("sex","boy");
                Animation rotate = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
                boy.startAnimation(rotate);
                rotate.reset();
                rotate.start();
                rotate.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        finishFragment();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                boy.setOnClickListener(null);
                girl.setOnClickListener(null);
            }
        });

        girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appDataPref.setPrefs("sex", "girl");
                Animation rotate = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
                girl.startAnimation(rotate);
                rotate.reset();
                rotate.start();
                rotate.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        finishFragment();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                boy.setOnClickListener(null);
                girl.setOnClickListener(null);
            }
        });
    }

    private void finishFragment() {
        ((MainActivity)getActivity()).startApp();
        ((MainActivity)getActivity()).popUpFragment();
    }

}
