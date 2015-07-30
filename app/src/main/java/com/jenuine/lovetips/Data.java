package com.jenuine.lovetips;

import java.util.ArrayList;

/**
 * Created by jenu on 30/6/15.
 */
public class Data {

    private ArrayList<Post> posts = new ArrayList<Post>();
    public static final Data data = new Data();

    public Data() {

    }

    public static Data getData() {
        return data;
    }

    public ArrayList<Post> getArrayList() {
        return posts;
    }
}
