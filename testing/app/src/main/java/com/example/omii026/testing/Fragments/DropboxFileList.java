package com.example.omii026.testing.Fragments;

import android.os.AsyncTask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI.Entry;
import com.dropbox.client2.exception.DropboxException;

import java.util.ArrayList;


/**
 * Created by panacloud on 9/18/15.
 */
public class DropboxFileList extends AsyncTask<Void,Void,ArrayList> {

    private DropboxAPI dropboxApi;
    private String path;
    private Handler handler;

    public DropboxFileList(DropboxAPI dropboxApi, String path, Handler handler) {
        this.dropboxApi = dropboxApi;
        this.path = path;
        this.handler = handler;
    }



    @Override
    protected ArrayList doInBackground(Void... params) {
        ArrayList files = new ArrayList();
        try {
            Entry directory = dropboxApi.metadata(path, 1000, null, true, null);
            for (Entry entry : directory.contents) {
                files.add(entry.fileName());
            }
        } catch (DropboxException e) {
            e.printStackTrace();
        }
        return files;
    }
    @Override
    protected void onPostExecute(ArrayList result) {
        Message message = handler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("data", result);
        message.setData(bundle);
        handler.sendMessage(message);
    }
}
