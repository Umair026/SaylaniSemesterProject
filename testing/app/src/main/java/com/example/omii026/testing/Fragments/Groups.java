package com.example.omii026.testing.Fragments;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omii026.testing.Firebase.FireBaseHandler;
import com.example.omii026.testing.MeApp;
import com.example.omii026.testing.R;
import com.example.omii026.testing.Services.ServiceError;
import com.example.omii026.testing.Services.ServiceListener;
import com.example.omii026.testing.Services.UserService;
import com.example.omii026.testing.SupportClasses.GroupChatData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class Groups extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String Item;
    private String mParam2;
    private View view;
    private ImageView imageView;
    private ImageView chat_icon;
    private OnFragmentInteractionListener mListener;
    private ListView listView;
    private ArrayList<GroupChatData> list = new ArrayList<>();
    private ImageView create;
    private EditText groupIdEditText,groupDescEditText;
    private String desc,id;
    private ProgressDialog progressDialog;
    private GroupListAdapter adapter;

    // TODO: Rename and change types and number of parameters
    public static Groups newInstance(String param1) {
        Groups fragment = new Groups();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public Groups() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onCreate:", "Group");

        if (getArguments() != null) {
            Item = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_groups, container, false);
        create = (ImageView) view.findViewById(R.id.create_group);
        listView = (ListView) view.findViewById(R.id.group_list);

               Log.d("onCreateView:","Group");

//        list.add("Group 1");
//        list.add("Group 2");
//        list.add("Group 3");
//        list.add("Group 4");
//        list.add("Group 5");
//        list.add("Group 6");
//        list.add("Group 7");
//      Log.d("MeappUser:",MeApp.getAppUser().getPassword());
//        if(MeApp.getAppUser() != null) {

            FireBaseHandler.getInstance().getUserGroupRef().child(MeApp.getAppUser().getUserId())
                    .addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            if (dataSnapshot != null) {
                                String groupKey = dataSnapshot.getKey();

                                FireBaseHandler.getInstance().getGroupRef().child(groupKey)
                                        .addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if(dataSnapshot != null) {

                                                    Log.d("dataSnapshot:", "" + dataSnapshot);
                                                    HashMap<String, Object> data = (HashMap<String, Object>) dataSnapshot.getValue();
                                                    String id = data.get("id").toString();
                                                    String image = data.get("group-image").toString();
                                                    String desc = data.get("desc").toString();
                                                    String owner = data.get("created-by").toString();
//
                                                    GroupChatData groupChatData = new GroupChatData(id, desc, image, owner);
                                                    list.add(groupChatData);
                                                    adapter.notifyDataSetChanged();
                                                }
                                            }

                                            @Override
                                            public void onCancelled(FirebaseError firebaseError) {
                                                Toast.makeText(getActivity(), "" + firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });

                            }
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {
                            list.remove(dataSnapshot.getValue());
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });

        adapter = new GroupListAdapter(getActivity(),list);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GroupChatData groupChatData = (GroupChatData) parent.getItemAtPosition(position);
                mListener = (OnFragmentInteractionListener) getActivity();
                mListener.OpenGroupChatFragment(groupChatData.getGroupName());
            }
        });




        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Bitmap bmp =  BitmapFactory.decodeResource(getResources(), R.drawable.one_football_app_icon);//your image
//                Log.d("befoer conversion:",""+R.drawable.one_football_app_icon);
//                Log.d("convert to bitmap:",""+bmp);
//                ByteArrayOutputStream bYtE = new ByteArrayOutputStream();
//                bmp.compress(Bitmap.CompressFormat.PNG, 100, bYtE);
//                bmp.recycle();
//                byte[] byteArray = bYtE.toByteArray();
//                String imageFile = Base64.encodeToString(byteArray, Base64.URL_SAFE);
//                Log.d("convert to base64:",""+imageFile);

//                FireBaseHandler.getInstance().getUserRef()
//                        .child(MeApp.getAppUser().getUserId()).child("profile-image").setValue(imageFile);


                ShowDialog();
            }
        });


        return view;
    }

    public void ShowDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.group_creation_dialog);

        groupIdEditText = (EditText) dialog.findViewById(R.id.editGroupId);
        groupDescEditText = (EditText) dialog.findViewById(R.id.editGroupDesc);

        dialog.setCancelable(false);
        ((Button) dialog.findViewById(R.id.creatButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desc = groupDescEditText.getText().toString();
                id = groupIdEditText.getText().toString();

                if (!id.equals("") && !desc.equals("")) {

                    progressDialog = ProgressDialog.show(getActivity(), "Group Creation", "Creating", true, false);

                    GroupChatData groupChatData = new GroupChatData(id,desc,"noImage");
       // Groupc cretion
                    UserService.createGroup(groupChatData, new ServiceListener() {
                        @Override
                        public void success(Object object) {
                            progressDialog.dismiss();
                            dialog.dismiss();
                            Toast.makeText(getActivity(), "Group created", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void error(ServiceError serviceError) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Error" + serviceError.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                } else {
                    Toast.makeText(getActivity(), "fields incomplete", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ((ImageView) dialog.findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("onActivityCreated:", "Group");

    }

    public interface OnFragmentInteractionListener{
        void OpenGroupChatFragment(String ss);
    }


}
