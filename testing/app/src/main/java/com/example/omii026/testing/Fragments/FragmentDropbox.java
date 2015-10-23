package com.example.omii026.testing.Fragments;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import android.os.Handler;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.TokenPair;


import com.dropbox.client2.session.Session;
import com.example.omii026.testing.R;

import java.util.ArrayList;
//import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class FragmentDropbox extends Fragment  {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private final static String FILE_DIR = "/DropboxAppData/";
    private static final String DROPBOX_NAME = "dropbox.prefs";
    private static final String ACCESS_KEY = "dbgeo8w88piw2bq";
    private static final String ACCESS_SECRET = "45zdnuzp9r2ira8";
    private static final Session.AccessType ACCESS_TYPE =  Session.AccessType.AUTO;


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View view;
    private boolean isUserLoggedIn;
    private Button uploadFileBtn;
    private Button listFilesBtn;
    private Button loginBtn;
    private DropboxAPI dropbox;
    private LinearLayout contain;
    private File selectedFile;

    public static File file;
    public static FileOutputStream outputStream;
    public static DropboxAPI.DropboxFileInfo info;

    // TODO: Rename and change types and number of parameters
    public static FragmentDropbox newInstance(String param1, String param2) {
        FragmentDropbox fragment = new FragmentDropbox();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentDropbox() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view = inflater.inflate(R.layout.fragment_fragment_dropbox,container,false);
//        contain = (LinearLayout) view.findViewById(R.id.container_files);
        uploadFileBtn = (Button) view.findViewById(R.id.upload);
        loginBtn = (Button) view.findViewById(R.id.login);
//        listFilesBtn = (Button) view.findViewById(R.id.listFile);


        ((Button) view.findViewById(R.id.login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isUserLoggedIn) {
                    dropbox.getSession().unlink();
                    loggedIn(false);
                }else{
                    ((AndroidAuthSession)dropbox.getSession()).startAuthentication(getActivity().getApplicationContext());
                }
            }
        });
        ((Button) view.findViewById(R.id.upload)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UploadFileToDropbox upload = new UploadFileToDropbox(getActivity(), dropbox,
                        FILE_DIR,selectedFile);
                upload.execute();
            }
        });
        ((Button) view.findViewById(R.id.loadFile)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("file/*");
//                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 0);

//                DropboxFileList listFiles = new DropboxFileList(dropbox, FILE_DIR,
//                        handler);
//                listFiles.execute();
            }
        });
        ((Button) view.findViewById(R.id.download)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                DownloadFileFromDropbox downloadFileFromDropbox = new DownloadFileFromDropbox(getActivity(),dropbox,FILE_DIR,selectedFile);
//                downloadFileFromDropbox.execute();
            }
        });

        loggedIn(false);

        AppKeyPair appKeyPair = new AppKeyPair(ACCESS_KEY,ACCESS_SECRET);

        AndroidAuthSession session;
        SharedPreferences prefs = getActivity().getSharedPreferences(DROPBOX_NAME,0);
        String key = prefs.getString(ACCESS_KEY,null);
        String secret = prefs.getString(ACCESS_SECRET,null);

        if(key != null && secret != null){
            AccessTokenPair token =  new AccessTokenPair(key,secret);
            session = new AndroidAuthSession(appKeyPair, ACCESS_TYPE.AUTO,token);
        }else{
            session = new AndroidAuthSession(appKeyPair,ACCESS_TYPE.AUTO);
        }
        dropbox =new DropboxAPI<AndroidAuthSession>(session);

        return view;
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
            Log.d("", "Please input a valid URL");
        } catch (IOException ioe) {
            Log.d("", "Can not connect to the URL");
        }

        return redirectedUrl;
    }


    private final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            ArrayList<String> result = msg.getData().getStringArrayList("data");
            for (String fileName : result) {
                Log.i("ListFiles", fileName);
                TextView tv = new TextView(getActivity());
                tv.setText(fileName);
//                contain.addView(tv);
            }
        }
    };

    public void loggedIn(boolean userLoggedIn) {

        isUserLoggedIn = userLoggedIn;
        uploadFileBtn.setEnabled(userLoggedIn);
//        uploadFileBtn.setBackgroundColor(userLoggedIn ? Color.BLUE : Color.GRAY);

//        listFilesBtn.setEnabled(userLoggedIn);

//        listFilesBtn.setBackgroundColor(userLoggedIn ? Color.BLUE : Color.GRAY);
        loginBtn.setText(userLoggedIn ? "Logout" : "Log in");

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode==getActivity().RESULT_OK) {
           selectedFile = new File(data.getData().getPath());

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        AndroidAuthSession session = (AndroidAuthSession) dropbox.getSession();
        if(session.authenticationSuccessful()){
            try {
                session.finishAuthentication();
                TokenPair token = session.getAccessTokenPair();
                SharedPreferences prefs = getActivity().getSharedPreferences(DROPBOX_NAME, 0);
                Editor editor = prefs.edit();
                editor.putString(ACCESS_KEY, token.key);
                editor.putString(ACCESS_SECRET, token.secret);
                editor.commit();
                loggedIn(true);
            }catch (IllegalStateException e){
                Toast.makeText(getActivity(),"Error Auth"+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
