package com.jenuine.lovetips;

/**
 * Created by jenu on 21/6/15.
 */
public class SubPostData {

    private String title, content;
    private boolean titlecolor;

    public SubPostData(String title, String content) {
        this.title = title;
        this.content = content;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
