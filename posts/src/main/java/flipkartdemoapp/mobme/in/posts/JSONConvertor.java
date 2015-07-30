package flipkartdemoapp.mobme.in.posts;

import com.google.gson.Gson;

/**
 * Created by jenu on 20/7/15.
 */
public class JSONConvertor {

    public static void main(String[] args){

        Gson gson = new Gson();
        // convert java object to JSON format,
        // and returned as JSON formatted string
        String json = gson.toJson(new Data());
        System.out.println(json);

    }
}
