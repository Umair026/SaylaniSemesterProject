package com.example.omii026.testing.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.exception.DropboxException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by panacloud on 10/19/15.
 */
public class DownloadFile extends AsyncTask<Void,Void,Boolean> {

    private File selectedFile;
    private Context context;
    private DropboxAPI dropboxAPI;

    public DownloadFile(Context context,File selectedFile,DropboxAPI dropboxAPI){
        this.context = context;
        this.selectedFile = selectedFile;
        this.dropboxAPI = dropboxAPI;
    }



    @Override
    protected Boolean doInBackground(Void... params) {

        FileOutputStream outputStream = null;
        File file = new File("/mnt/sdcard/Download/SampleFile.pdf");

        try {

            outputStream = new FileOutputStream(file);
            DropboxAPI.DropboxFileInfo info = dropboxAPI.getFile("/DropboxSample/Task.pdf",null,outputStream,null);
            Log.d("info",""+info);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (DropboxException e) {
            e.printStackTrace();
        }finally {
            if(outputStream != null){
                try{
                    outputStream.close();
                }catch (IOException e){

                }
            }
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            Toast.makeText(context, "File has been successfully download!",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "An error occured while processing the download request.",
                    Toast.LENGTH_LONG).show();
        }
    }
}
