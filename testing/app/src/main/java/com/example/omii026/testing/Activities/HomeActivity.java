package com.example.omii026.testing.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omii026.testing.Class.User;
import com.example.omii026.testing.Firebase.FireBaseHandler;
import com.example.omii026.testing.Fragments.ChatFragment;
import com.example.omii026.testing.Fragments.FindFriends;
import com.example.omii026.testing.Fragments.FragmentDropbox;
import com.example.omii026.testing.Fragments.FragmentMap;
import com.example.omii026.testing.Fragments.Friends;
import com.example.omii026.testing.Fragments.Gallery;
import com.example.omii026.testing.Fragments.GroupChatFragment;
import com.example.omii026.testing.Fragments.GroupListAdapter;
import com.example.omii026.testing.Fragments.Groups;
import com.example.omii026.testing.Fragments.Home;
import com.example.omii026.testing.Fragments.MembersList;
import com.example.omii026.testing.Fragments.MusicPlayer;
import com.example.omii026.testing.Fragments.PagerAdapter;
import com.example.omii026.testing.Fragments.PagerHome;
import com.example.omii026.testing.MeApp;
import com.example.omii026.testing.R;
import com.example.omii026.testing.Services.MenuManagerService;
import com.example.omii026.testing.SupportClasses.UserData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

import java.security.acl.Group;
import java.util.HashMap;

public class HomeActivity extends ActionBarActivity implements
        NavigationDrawerFragment.NavigationDrawerCallbacks,
        Home.onFragmentInteractionListener,
        Groups.OnFragmentInteractionListener,
        Friends.OnFragmentInteractionListener,
        GroupListAdapter.AdapterInteface{

    //moveTaskToBack(True);

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private  NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private TextView appTitle;
private static DrawerLayout mDrawerLayout;
    public Toolbar toolbar;
    TextView toolbarTitle;
    private MenuManagerService menuManagerService = null;
    private User user;
//    private FragmentManager mFragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menuManagerService = new MenuManagerService();
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
         toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
       mTitle = "ME APP";
        toolbarTitle = (TextView) toolbar.findViewById(R.id.title_appbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
//
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(HomeActivity.this, "working", Toast.LENGTH_SHORT).show();
//            }
//        });



        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);


        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout),toolbar);
    }



    public void openDrawer(){
        mDrawerLayout.openDrawer(mDrawerLayout);
    }
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
//                .commit();
        fragmentManager.beginTransaction()
                .add(R.id.container, new PagerHome())
                .commit();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if(getSupportFragmentManager().getBackStackEntryCount() == 0){
            moveTaskToBack(true);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public void onHomeClick(String Item) {
//        if(mFragmentManager == null) {
//            mFragmentManager = getSupportFragmentManager();
//        }
//        else{
//            mFragmentManager.beginTransaction()
//                    .add(R.id.container, Home.newInstance(Item))
//                    .addToBackStack(null).commit();
//        }
    }

    @Override
    public void onGroupClick(String Item) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
            mFragmentManager.beginTransaction()
                    .add(R.id.container, Groups.newInstance(Item))
                    .addToBackStack(null).commit();


    }

    @Override
    public void onFriendsClick(String Item) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
            mFragmentManager.beginTransaction()
                    .add(R.id.container, Friends.newInstance(Item))
                    .addToBackStack(null).commit();
    }

    @Override
    public void onMapClick(String Item) {
//TODO
        FragmentManager mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .add(R.id.container, new FragmentMap())
                .addToBackStack(null).commit();

    }

    @Override
    public void onFindFriendsClick(String Item) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
            mFragmentManager.beginTransaction()
                    .add(R.id.container, new FindFriends())
                    .addToBackStack(null).commit();
    }

    @Override
    public void onMusicPlayerClick(String Item) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
            mFragmentManager.beginTransaction()
                    .add(R.id.container, MusicPlayer.newInstance(Item))
                    .addToBackStack(null).commit();
    }

    @Override
    public void onGalleryClick(String Item) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
            mFragmentManager.beginTransaction()
                    .add(R.id.container,new Gallery())
                    .addToBackStack(null).commit();
    }

    @Override
    public void onAboutUsClick(String Item) {
//TODO
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
//        actionBar.setTitle(mTitle);
        toolbarTitle.setText(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.home, menu);
            menuManagerService.setUpMenuItems(R.id.ic_nav);
            menuManagerService.updateMenu();
            menuManagerService.setMenu(menu);
            menuManagerService.updateMenu();
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void UserFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container,new FindFriends()).addToBackStack(null).commit();
    }

    @Override
    public void OpenDropox() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new FragmentDropbox()).addToBackStack(null).commit();
    }

    @Override
    public void OpenGroupChatFragment(String ss) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container,GroupChatFragment.newInstance(ss)).addToBackStack(null).commit();
    }

    @Override
    public void OpenChatFragment(String uid) {

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, ChatFragment.newInstance("chat",uid))
                .addToBackStack(null).commit();
    }

    @Override
    public void addGroupMember(String nam) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .add(R.id.container, MembersList.newInstance(nam,""))
                .addToBackStack(null).commit();
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private onFragmentInteractionListener mListener;

        private static final String ARG_SECTION_NUMBER = "section_number";
        private View rootView;

        private ImageView imageView;
        private DrawerLayout mDrawerLayout;
        private ListView listView;
        private ImageView groupIcon;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_home3, container, false);
//            getActivity().findViewById(R.id.drawer_layout);
//            getActivity().findViewById(R.id.drawer_layout);

//            imageView = (ImageView) rootView.findViewById(R.id.ic_nav);
//            imageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    NavigationDrawerFragment.mDrawerLayout.openDrawer(NavigationDrawerFragment.mDrawerListView);
//                }
//            });
//
//            groupIcon = (ImageView) rootView.findViewById(R.id.ic_user);
//            groupIcon.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    mListener = (onFragmentInteractionListener) getActivity();
//                    mListener.UserFragment();
//                }
//            });
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((HomeActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
          }

public interface onFragmentInteractionListener{
        void UserFragment();
    }


}
