package com.jenuine.lovetips.itemanimator;

import com.google.gson.Gson;
import com.jenuine.lovetips.Data;
import com.jenuine.lovetips.Images;

/**
 * Created by jenu on 20/7/15.
 */
public class JSONConvertor {

    public static void main(String[] args){

        Gson gson = new Gson();
        // convert java object to JSON format,
        // and returned as JSON formatted string
        String json = gson.toJson(new Images());
        System.out.println(json);

    }
}
