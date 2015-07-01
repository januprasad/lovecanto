package com.jenuine.lovetips;

import java.util.Random;

/**
 * Created by jenu on 29/6/15.
 */
public class ImageLibs {
    public static int getImage() {
        Random random = new Random();
        int x = random.nextInt(11);
        if (x == 1) {
            return R.drawable.i_love_you;
        } else if (x == 2) {
            return R.drawable.gift_gf;
        } else if (x == 3) {
            return R.drawable.love1;
        }else if (x == 4) {
            return R.drawable.sea_love;
        } else if (x == 5) {
            return R.drawable.bridge_love;
        }else if (x == 6) {
            return R.drawable.hill_love_large;
        }else if (x == 7) {
            return R.drawable.leg_kiss;
        }else if (x == 8) {
            return R.drawable.night_love;
        }else if (x == 9) {
            return R.drawable.street_love;
        }else if (x == 10) {
            return R.drawable.love1;
        }else {
            return R.drawable.wedding_love;
        }
    }
}
