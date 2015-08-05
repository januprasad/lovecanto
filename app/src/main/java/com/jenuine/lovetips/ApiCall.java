package com.jenuine.lovetips;

/**
 * Created by jenu on 9/7/15.
 */

import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * **************************************************************
 * Background Async Task to Delete Product
 */
public class ApiCall extends AsyncTask<String, String, String> {

    private String type;
    ProgressDialog pDialog;
    String key = "";

    /**
     * Before starting background thread Show Progress Dialog
     */
    Callback callback;

    public ApiCall(Callback callback,String type) {
        this.callback = callback;
        this.type=type;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//        pDialog = new ProgressDialog(mainActivity);
//        pDialog.setMessage("Communicating with server...");
//        pDialog.setIndeterminate(false);
//        pDialog.setCancelable(true);
//        pDialog.show();
    }

    /**
     * Deleting product
     */
    protected String doInBackground(String... args) {

        // Check for success tag
        int success;
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("amount", "24599"));
//        params.add(new BasicNameValuePair("currency", "inr"));
//        params.add(new BasicNameValuePair("merchant_name", "DemoKart"));
//        params.add(new BasicNameValuePair("bank", "demobank"));
//        params.add(new BasicNameValuePair("device", "android"));

        // getting product details by making HTTP request
//        JSONObject json = new JSONParser().makeHttpRequest(
//                "http://www.omdbapi.com/?t=terminator&y=&plot=short&r=json", "GET", params);
        JSONObject json=null;
        if(type.equalsIgnoreCase("boy")) json = new CustomHTTPClient().makeHttpRequest(
                "https://raw.githubusercontent.com/jenuprasad/myapp-api/master/postsBoy.json", "GET", params);
        else if(type.equalsIgnoreCase("girl")) json = new CustomHTTPClient().makeHttpRequest(
                "https://raw.githubusercontent.com/jenuprasad/myapp-api/master/postsGirl.json", "GET", params);

        // check your log for json response


        // json success tag
//            success = json.getInt(TAG_SUCCESS);
//            if (success == 1) {
        // product successfully deleted
        // notify previous activity by sending code 100
//                Intent i = getIntent();
//                // send result code 100 to notify about product deletion
//                setResult(100, i);
//                finish();
//            }
        if (json != null)
            return json.toString();
        else return null;
    }

    /**
     * After completing background task Dismiss the progress dialog
     * *
     */
    protected void onPostExecute(String result) {
        // dismiss the dialog once product deleted

//        pDialog.dismiss();
        if (result != null)
            callback.onSuccess(result);
        else
            callback.onSuccess("");
    }

}