package com.example.omii026.testing.Fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omii026.testing.R;


/**
 * A simple {@link android.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.example.omii026.testing.Fragments.MusicPlayer.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link com.example.omii026.testing.Fragments.MusicPlayer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MusicPlayer extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String Item;
    private View view;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private ImageView imageView;
    private String[] lower_item;
    private int[] image_Id;
    private GridView gridView;
    private PlayerGridAdapter mPlayerGridAdapter;


    // TODO: Rename and change types and number of parameters
    public static MusicPlayer newInstance(String param1) {
        MusicPlayer fragment = new MusicPlayer();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);

        fragment.setArguments(args);
        return fragment;
    }

    public MusicPlayer() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         lower_item = new String[] {
                "All Music","Recent player","Artist","Album","Folder","Playlist","Recent Add"
        };
         image_Id = new int[]{
                R.drawable.ic_audiotrack_white_24dp,
                R.drawable.ic_library_music_white_24dp,
                R.drawable.ic_person_outline_white_24dp,
                R.drawable.ic_album_white_24dp,
                R.drawable.ic_folder_open_white_24dp,
                R.drawable.ic_queue_music_white_24dp,
                R.drawable.ic_content_paste_white_24dp,
        };

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_music_player, container, false);
        gridView = (GridView) view.findViewById(R.id.grid_view);

        mPlayerGridAdapter = new PlayerGridAdapter(getActivity(),lower_item,image_Id);
        gridView.setAdapter(mPlayerGridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
                Toast.makeText(getActivity().getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                TextView textView = (TextView) view.findViewById(R.id.grid_lower_item);
            }
        });
        final ImageView play_Image = (ImageView) view.findViewById(R.id.play);
        play_Image.setOnClickListener(new View.OnClickListener() {
            int set;
            @Override
            public void onClick(View v) {

                if(set != 1) {
                    Toast.makeText(getActivity(),"Play click",Toast.LENGTH_SHORT).show();
                    play_Image.setImageResource(R.drawable.ic_pause_white_24dp);
                    set = 1;
                }
                else {
                    Toast.makeText(getActivity(),"Pause click",Toast.LENGTH_SHORT).show();
                    play_Image.setImageResource(R.drawable.ic_play_arrow_white_24dp);
                    set =0;
                }

            }
        });




        return view;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
