package com.example.omii026.testing.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.exception.DropboxException;
import com.example.omii026.testing.Firebase.FireBaseHandler;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

/**
 * Created by Omii026 on 10/18/2015.
 */
public class DownloadFileFromDropbox extends AsyncTask<Void,Void,Boolean> {

    private DropboxAPI dropboxApi;
    private String path;
    private Context context;
    private File selectedFile;
    private DropboxAPI.Entry ref;

    private static final String TAG = "DownloadFile";

    DownloadFileFromDropbox(Context context,DropboxAPI dropboxAPI,String path,File selectedFile){

        this.context = context;
        this.dropboxApi = dropboxAPI;
        this.path = path;
        this.selectedFile = selectedFile;
    }

    @Override
    protected Boolean doInBackground(Void... params) {

        try {

            String address = null;
                    DropboxAPI.DropboxLink Link = dropboxApi.share(path+selectedFile.getName());
                   Log.d(TAG,"Link.url:"+Link.url);
                    address = getShareURL(Link.url).replaceFirst("https://www", "https://dl");
                    Log.d(TAG,"shareLink:"+address);
            if(!address.equals("")){
                FragmentDropbox.shareLink = address;
//                HashMap<String,Object> obj = new HashMap<>();
//                obj.put("link",address);
//                FireBaseHandler.getInstance().getRootFirebaseRef().child("Links").child("shareLink").setValue(obj);

//                String link = "<a href='www.facebook.com'>facebook<> "
            }

            return true;
        } catch(DropboxException e){
            e.printStackTrace();
        }

        return false;
    }
    String getShareURL(String strURL) {
        URLConnection conn = null;
        String redirectedUrl = null;
        try {
            URL inputURL = new URL(strURL);
            conn = inputURL.openConnection();
            conn.connect();

            InputStream is = conn.getInputStream();
//            System.out.println("Redirected URL: " + conn.getURL());
            redirectedUrl = conn.getURL().toString();
            is.close();

        } catch (MalformedURLException e) {
            Log.d(TAG, "Please input a valid URL");
        } catch (IOException ioe) {
            Log.d(TAG, "Can not connect to the URL");
        }

        return redirectedUrl;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            Toast.makeText(context, "File has been successfully downloaded!",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "An error occured while processing the download request.",
                    Toast.LENGTH_LONG).show();
        }
    }

}
