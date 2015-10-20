package com.example.omii026.testing.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.exception.DropboxException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by panacloud on 9/18/15.
 */
public class UploadFileToDropbox extends AsyncTask<Void,Void,Boolean> {
    private DropboxAPI dropboxApi;
    private String path;
    private Context context;
    private File selectedFile;

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
            // Creating a temporal file.
//            tempFileToUploadToDropbox = File.createTempFile("hello_world", ".txt", tempDropboxDirectory);
//            fileWriter = new FileWriter(tempFileToUploadToDropbox);
//            fileWriter.write("Hello World drom Android Dropbox Implementation nasdlgvnadlvnaln aeojiwjwufheiquwfhehjwcvqytfr7123yfguample!");
//            fileWriter.close();

            // Uploading the newly created file to Dropbox.
//            FileInputStream fileInputStream = new FileInputStream(tempFileToUploadToDropbox);
//            dropboxApi.putFile(path + "hello_world.txt", fileInputStream,
//                    tempFileToUploadToDropbox.length(), null, null);
//            tempFileToUploadToDropbox.delete();

            FileInputStream fileInputStream = new FileInputStream(selectedFile);
            dropboxApi.putFile(path + selectedFile.getName(), fileInputStream,
                    selectedFile.length(), null, null);

            Log.d("absolutePath:",""+selectedFile.getAbsolutePath());




            return true;
        } catch (IOException e) {
            Log.d("Error","IOException"+e);
            e.printStackTrace();
        } catch (DropboxException e) {
            Log.d("Error","DropboxException"+e);

            e.printStackTrace();
        }

        return false;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            Toast.makeText(context, "File has been successfully uploaded!",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "An error occured while processing the upload request.",
                    Toast.LENGTH_LONG).show();
        }
    }
}
