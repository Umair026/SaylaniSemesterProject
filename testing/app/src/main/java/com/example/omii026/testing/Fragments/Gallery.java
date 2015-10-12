package com.example.omii026.testing.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import com.example.omii026.testing.R;
import com.example.omii026.testing.SupportClasses.HorizontalListView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Gallery extends Fragment {

    // TODO: Rename and change types of parameters
    private String Item;
    private String mParam2;
    private View view;
private GalleryImagesAdapter galleryImagesAdapter;
    private ListView listView;
    private ImageView imageView;
private ArrayList<Bitmap> bitmapsList = new ArrayList<>();
    private ImageView holder;
    private Bitmap bitmap = null;
    private String mCurrentPhotoPath;
    private ImageView imageView2;
    private HorizontalListView galleryList;
    private ImageView imageShow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_gallery, container, false);
        galleryList = (HorizontalListView) view.findViewById(R.id.galleryListView);
        imageView = (ImageView) view.findViewById(R.id.camera);
        imageShow = (ImageView) view.findViewById(R.id.imageShow);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });



        galleryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                imageShow.setImageBitmap(bitmapsList.get(position));
            }
        });
        Bitmap bitmap0 = BitmapFactory.decodeResource(getResources(),R.drawable.profileimage);
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.image1);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.drawable.image2);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(),R.drawable.image1);
        Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(),R.drawable.image2);
        bitmapsList.add(bitmap0);
        bitmapsList.add(bitmap1);
        bitmapsList.add(bitmap2);
        bitmapsList.add(bitmap3);
        bitmapsList.add(bitmap4);
        galleryImagesAdapter = new GalleryImagesAdapter(getActivity().getApplicationContext(),bitmapsList);
        galleryList.setAdapter(galleryImagesAdapter);
        galleryImagesAdapter.notifyDataSetChanged();

        return view;
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  // prefix
                ".jpg",         // suffix
                storageDir      // directory
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
     if(requestCode == 1 && resultCode == Activity.RESULT_OK &&data != null) {
        try {

            Uri selectedImage = data.getData();
            bitmap  = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),selectedImage);
            bitmapsList.add(bitmap);
            galleryImagesAdapter.notifyDataSetChanged();
//        bitmap = (Bitmap) data.getExtras().get("data");
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//        byte[] byteArray = stream.toByteArray();
//
//        // convert byte array to Bitmap
//
//        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
//                byteArray.length);
//
//        holder.setImageBitmap(bitmap);
//        bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), Uri.parse(mCurrentPhotoPath));
//        holder.setImageBitmap(bitmap);
    } catch (Exception e) {
        e.printStackTrace();
    }

}

    }
}




