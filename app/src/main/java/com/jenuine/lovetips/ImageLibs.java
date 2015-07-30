package com.jenuine.lovetips;

import com.google.gson.Gson;

import java.util.Random;

/**
 * Created by jenu on 29/6/15.
 */
public class ImageLibs {
    static int index = 0;

    private  static Images images=null;
    public static String getImage(String imagesString) {
       images = new Gson().fromJson(imagesString, Images.class);
        int x = 0;
        if (index == 0 || index >= images.images.size()) {
            index = new Random().nextInt(images.images.size());
            x = index;
            index++;
        } else {
            index++;
            x = index;
        }
        if (x == 1) {
            return images.images.get(0);
        } else if (x == 2) {
            return images.images.get(1);
        } else if (x == 3) {
            return images.images.get(2);
        } else if (x == 4) {
            return images.images.get(3);
        } else if (x == 5) {
            return images.images.get(4);
        } else if (x == 6) {
            return images.images.get(5);
        } else if (x == 7) {
            return images.images.get(6);
        } else if (x == 8) {
            return images.images.get(7);
        } else if (x == 9) {
            return images.images.get(8);
        } else if (x == 10) {
            return images.images.get(9);
        } else if (x == 11) {
            return images.images.get(10);
        } else if (x == 12) {
            return images.images.get(11);
        } else if (x == 13) {
            return images.images.get(12);
        } else if (x == 14) {
            return images.images.get(13);
        } else if (x == 15) {
            return images.images.get(14);
        } else if (x == 16) {
            return images.images.get(15);
        } else if (x == 17) {
            return images.images.get(16);
        } else if (x == 18) {
            return images.images.get(17);
        } else if (x == 19) {
            return images.images.get(18);
        } else if (x == 20) {
            return images.images.get(19);
        } else if (x == 21) {
            return images.images.get(20);
        } else if (x == 22) {
            return images.images.get(21);
        } else if (x == 23) {
            return images.images.get(22);
        } else if (x == 24) {
            return images.images.get(23);
        } else if (x == 25) {
            return images.images.get(24);
        } else if (x == 26) {
            return images.images.get(25);
        } else if (x == 27) {
            return images.images.get(26);
        } else if (x == 28) {
            return images.images.get(27);
        } else if (x == 29) {
            return images.images.get(28);
        } else if (x == 30) {
            return images.images.get(29);
        } else if (x == 31) {
            return images.images.get(30);
        } else if (x == 32) {
            return images.images.get(31);
        } else if (x == 33) {
            return images.images.get(32);
        } else if (x == 34) {
            return images.images.get(33);
        } else if (x == 35) {
            return images.images.get(34);
        } else if (x == 36) {
            return images.images.get(35);
        } else {
            return images.images.get(1);
        }
    }
}

