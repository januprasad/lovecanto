package com.jenuine.lovetips;

import java.util.List;

/**
 * Created by jenu on 30/6/15.
 */
public class Post {

    private String title;
    private List<SubPostData> subPostDatas;

    public List<SubPostData> getSubPostDatas() {
        return subPostDatas;
    }


    public Post(String title, List<SubPostData> subPostDatas) {
        this.title = title;
        this.subPostDatas = subPostDatas;
    }

    public Post(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
