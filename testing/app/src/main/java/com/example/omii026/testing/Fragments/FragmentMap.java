package com.example.omii026.testing.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.omii026.testing.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Omii026 on 10/24/2015.
 */
public class FragmentMap extends Fragment {
    private View view;
    private GoogleMap mMap;
    private Marker marker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_map,container,false);

        showMapFragment();

        return view;
    }

    private void showMapFragment() {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapView);

        if(mapFragment != null){
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    FragmentMap.this.mMap = googleMap;

                    LatLng orangi = new LatLng(24.9705317,66.98093);
                    googleMap.addMarker(new MarkerOptions().position(orangi).title("orangiTown"));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(orangi));

//                    googleMap.moveCamera(CameraUpdateFactory.zoomTo(20));

                    googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                    googleMap.setMyLocationEnabled(true);

                    googleMap.animateCamera(CameraUpdateFactory.zoomTo(15),2000,null);


                }
            });
        }else{

        }
    }
}
