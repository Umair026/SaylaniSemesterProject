package com.example.omii026.testing.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.exception.DropboxException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by panacloud on 9/18/15.
 */
public class UploadFileToDropbox extends AsyncTask<Void,Void,Boolean> {
    private DropboxAPI dropboxApi;
    private String path;
    private Context context;
    private File selectedFile;
    private DropboxAPI.Entry ref;
    private static final String TAG = "UploadFile";

    public UploadFileToDropbox(Context context, DropboxAPI dropboxApi,
                      String path,File selectedFile) {
        this.context = context.getApplicationContext();
        this.dropboxApi = dropboxApi;
        this.path = path;
        this.selectedFile = selectedFile;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        final File tempDropboxDirectory = context.getCacheDir();
        File tempFileToUploadToDropbox;
        FileWriter fileWriter = null;

        try {

            FileInputStream fileInputStream = new FileInputStream(selectedFile);
            dropboxApi.putFile(path + selectedFile.getName(), fileInputStream,
                    selectedFile.length(), null, null);
            Log.d(TAG,"absolute/path:"+selectedFile.getAbsolutePath());

            return true;
        } catch (IOException e) {
            Log.d(TAG,"IOException"+e);
            e.printStackTrace();
        } catch (DropboxException e) {
            Log.d(TAG,"DropboxException"+e);
            e.printStackTrace();
        }

        return false;
    }


    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            Toast.makeText(context, "File has been successfully uploaded!",
                    Toast.LENGTH_LONG).show();

            //get Share Url
            DownloadFileFromDropbox downloadFileFromDropbox =
                    new DownloadFileFromDropbox(context,dropboxApi,path,selectedFile);
            downloadFileFromDropbox.execute();


        } else {
            Toast.makeText(context, "An error occured while processing the upload request.",
                    Toast.LENGTH_LONG).show();
        }
    }
}
