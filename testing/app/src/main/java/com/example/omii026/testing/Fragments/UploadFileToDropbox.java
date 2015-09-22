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

    public UploadFileToDropbox(Context context, DropboxAPI dropboxApi,
                      String path) {
        this.context = context.getApplicationContext();
        this.dropboxApi = dropboxApi;
        this.path = path;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        final File tempDropboxDirectory = context.getCacheDir();
        File tempFileToUploadToDropbox;
        FileWriter fileWriter = null;

        try {
            // Creating a temporal file.
            tempFileToUploadToDropbox = File.createTempFile("hello_world", ".txt", tempDropboxDirectory);
            fileWriter = new FileWriter(tempFileToUploadToDropbox);
            fileWriter.write("Hello World drom Android Dropbox Implementation nasdlgvnadlvnaln aeojiwj " +
                    "v[pjfa9mvaiopmuiovanutiopquby iopuanvpuydspov8fgqmpfov8wyv aipuvnipu dynp8 am v8aiosu8ynv " +
                    "piuwspiuvsnpiucy wipurynpw i rtaipuwbypv89n piufpiuvnysuirnyaewipuvrniuwaecntiytvipusvn tgiuaviuy" +
                    "niugcnipuepwiucrxn eiuwar cgeqwtriuvqwegh vwe bc8iqw ipuchiprucnvipurnptgiugv fgviwbfviueqwgtfp78uqegwhc" +
                    "fiubewhifgqeipu2fgbehjwbvfuyvgwqduyfbgqihebgiuqreghwfiubwdjkbfiuqwghfiueq2bfeqwgfhq34jkbfgvequwghcioue ry89[e" +
                    "vbip7ewnr[oieqwh crycqwgbeqw ipruceqwiurbveqwipucn eqwiugrtbeqw iuftiqweu cfgdehfgvuvbch3rbfhuvhdvfqhjgeiurteqw" +
                    "ljqglrteqwljkfbdmnbfjlgfiluaewb cvehwruirytci rbgiv;eh;krejg ;kejqw bckehfcq3hgExjjjjjdskcvnsdklnvadklsnvklanvl'" +
                    "anv'kansdmvkladnsvkladfhnsgkladfnsghasdioufhadiouwfhkuadjsbnvioudwghfouajsdbnfvjkadhsfojvbadsjkbhvjksadgfvuierwhfgu" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "hgiuruqwyepruiohqewr789234y65789y234hoqwncd4yp3489cnvy234rdyr423t6hy7j8es4cdrfvgbhjnkmwse4dr5ft6yguhijkos4edr5" +
                    "ft6guy7huijkm[qhcn89wynryq2fhuydgcvyubdschjabdsvlqfetwy89frhbdvcipuqwert89ehwgfkuvgty    ew89ryiueqwgfdqwbcfhgdqwipuf e" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "jbdsauvhbriugbadfjksiurghjkdfsbvjkabdsjkvbgarwioughadfjksbvjaksdnbv;jabsdvjk;abdsvjk;abscnknvbcsjk;verwaioughuergeiru" +
                    "wufheiquwfhehjwcvqytfr7123yfguample!");
            fileWriter.close();

            // Uploading the newly created file to Dropbox.
            FileInputStream fileInputStream = new FileInputStream(tempFileToUploadToDropbox);
            dropboxApi.putFile(path + "hello_world.txt", fileInputStream,
                    tempFileToUploadToDropbox.length(), null, null);
//            tempFileToUploadToDropbox.delete();

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
