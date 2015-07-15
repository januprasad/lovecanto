package com.jenuine.lovetips;

/**
 * Created by jenu on 29/6/15.
 */
public class ImageLibs {
    static int index = 0;

    public static int getImage() {
        int x = index = index + 1;
        if (x == 37)
            index = 0;
        if (x == 1) {
            return R.drawable.love1;
        } else if (x == 2) {
            return R.drawable.love2;
        } else if (x == 3) {
            return R.drawable.love3;
        } else if (x == 4) {
            return R.drawable.love4;
        } else if (x == 5) {
            return R.drawable.love5;
        } else if (x == 6) {
            return R.drawable.love6;
        } else if (x == 7) {
            return R.drawable.love7;
        } else if (x == 8) {
            return R.drawable.love8;
        } else if (x == 9) {
            return R.drawable.love9;
        } else if (x == 10) {
            return R.drawable.love10;
        } else if (x == 11) {
            return R.drawable.love11;
        } else if (x == 12) {
            return R.drawable.love12;
        } else if (x == 13) {
            return R.drawable.love13;
        } else if (x == 14) {
            return R.drawable.love14;
        } else if (x == 15) {
            return R.drawable.love15;
        } else if (x == 16) {
            return R.drawable.love16;
        } else if (x == 17) {
            return R.drawable.love17;
        } else if (x == 18) {
            return R.drawable.love18;
        } else if (x == 19) {
            return R.drawable.love19;
        } else if (x == 20) {
            return R.drawable.love20;
        } else if (x == 21) {
            return R.drawable.love21;
        } else if (x == 22) {
            return R.drawable.love22;
        } else if (x == 23) {
            return R.drawable.love23;
        } else if (x == 24) {
            return R.drawable.love24;
        } else if (x == 25) {
            return R.drawable.love25;
        } else if (x == 26) {
            return R.drawable.love26;
        } else if (x == 27) {
            return R.drawable.love27;
        } else if (x == 28) {
            return R.drawable.love28;
        } else if (x == 29) {
            return R.drawable.love29;
        } else if (x == 30) {
            return R.drawable.love30;
        } else if (x == 31) {
            return R.drawable.love31;
        } else if (x == 32) {
            return R.drawable.love32;
        } else if (x == 33) {
            return R.drawable.love33;
        } else if (x == 34) {
            return R.drawable.love34;
        } else if (x == 35) {
            return R.drawable.love35;
        } else if (x == 36) {
            return R.drawable.love36;
        } else {
            return R.drawable.love2;
        }
    }
}
