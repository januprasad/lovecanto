package com.jenuine.lovetips;

/**
 * Created by jenu on 12/7/15.
 */
public abstract class Callback {
    public abstract void onSuccess(String response);
    public abstract void onFail(String response);
}
