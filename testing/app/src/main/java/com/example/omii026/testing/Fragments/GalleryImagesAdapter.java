package com.example.omii026.testing.Fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.omii026.testing.R;

import java.util.ArrayList;

/**
 * Created by Omii026 on 9/6/2015.
 */
public class GalleryImagesAdapter extends BaseAdapter {

    private Context context;
    private int[] array;
    private LayoutInflater inflater;
    private ArrayList<Bitmap> bitmapsList;

    public GalleryImagesAdapter(Context context,ArrayList<Bitmap> bitmapsList){
        this.context = context;
        this.array = array;
        this.bitmapsList = bitmapsList;
        inflater = ((Activity)context).getLayoutInflater();
    }

    @Override
    public int getCount() {
        return bitmapsList.size();
    }

    @Override
    public Bitmap getItem(int i) {
        return bitmapsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            view = inflater.inflate(R.layout.gallery_image_item,viewGroup,false);
        }
        ((ImageView)view.findViewById(R.id.imageView)).setImageBitmap(getItem(i));

        return view;
    }
}
