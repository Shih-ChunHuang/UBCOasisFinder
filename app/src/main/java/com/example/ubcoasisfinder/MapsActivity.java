package com.example.ubcoasisfinder;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;


import model.Oasis;
import model.OasisManager;
import parsers.OasisResourcesParser;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;

    private ListView mDrawerList;

    private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private ArrayAdapter<String> mAdapter;
    private String mActivityTitle;

    final LatLngBounds UBCBound = new LatLngBounds(
            new LatLng(49.24, -123.26), new LatLng(49.28, -123.23));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mDrawer = (NavigationView) findViewById(R.id.main_drawer);

        //mDrawerList = (ListView)findViewById(R.id.navList);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();


        getSupportActionBar().setTitle(mActivityTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //addDrawerItems();
        setupDrawer();
        createMarker();

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            // allows you to provide a view that will be used for the entire info window.
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            // allows you to just customize the contents of the window but still keep the default info
            // window frame and background.
            @Override
            public View getInfoContents(Marker marker) {

                Context context = MapsActivity.this;

                LinearLayout info = new LinearLayout(context);
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(context);
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.LEFT);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(context);
                snippet.setTextColor(Color.DKGRAY);
                snippet.setText(marker.getSnippet());
                snippet.setTextSize(12);

                info.addView(title);
                info.addView(snippet);

                return info;
            }
        });

        // Restrict bound to UBC area
        mMap.setLatLngBoundsForCameraTarget(UBCBound);

        drawMarker();

        // Add a marker in UBC and move the camera
        LatLng UBC = new LatLng(49.2606, -123.2460);

        // TODO
        // click check box and filter one kind of oasis

        // the default location will be UBC with zoom-in effect
        float zoomLevel = (float) 14.0; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UBC, zoomLevel));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    // TODO
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.drawer_item_mw:
                //Write your code
                return true;
            case R.id.drawer_item_vm:
                //Write your code
                return true;
            case R.id.drawer_item_wf:
                //Write your code
                return true;
            case R.id.drawer_item_about:
                //Write your code
                return true;
            case R.id.drawer_item_feedback:
                //Write your code
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Show info window when user clicks a marker
    @Override
    public boolean onMarkerClick(Marker marker){
        marker.showInfoWindow();
        return true;
    }

//    private void drawCategoryMarker(String name){
//        OasisManager oasisManager = OasisManager.getInstance();
//        for (Oasis oneOasis : oasisManager.getOasises()){
//
//            if(oneOasis.getAmenity().getDisplayName().equals(name)){
//
//                LatLng oneLatLng = new LatLng(oneOasis.getLat(), oneOasis.getLon());
//                Marker onepMarker = mMap.addMarker(new MarkerOptions().position(oneLatLng).title(oneOasis.getAmenity().getDisplayName()).
//                        icon(BitmapDescriptorFactory.defaultMarker(oneOasis.getAmenity().getColour())));
//
//                onepMarker.setSnippet(String.valueOf(parseInfo(oneOasis.getInfo())));
//
//            }
//        }
//    }

    /**
     * reading data from OasisManager and draw it on the map
     */
    private void drawMarker(){

        mMap.clear();

        OasisManager oasisManager = OasisManager.getInstance();

        for (Oasis oneOasis : oasisManager.getOasises()){
            LatLng oneLatLng = new LatLng(oneOasis.getLat(), oneOasis.getLon());
            Marker onepMarker = mMap.addMarker(new MarkerOptions().position(oneLatLng).title(oneOasis.getAmenity().getDisplayName()).
                    icon(BitmapDescriptorFactory.defaultMarker(oneOasis.getAmenity().getColour())));

            onepMarker.setSnippet(String.valueOf(parseInfo(oneOasis.getInfo())));

        }
    }

    /**
     * parse getInfo from Map to String
     */
    private String parseInfo(Map<String, String> info) {

        String infoSession = "";
        String colon = " : ";
        String newLine = "\n";

        for (String item :info.keySet()){
            if (!item.equals("ID")) {
                infoSession += item + colon + info.get(item) + newLine;
            }
        }

        return infoSession;
    }

    /**
     * get file from assets folder
     */
    private void createMarker(){

        try {
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("oasisdata.json");
            readSource(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     *  read file line by ine and parse data to OasisManager
     */
    private void readSource(InputStream inputStream){

        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }

            br.close();

            OasisResourcesParser.parseOasises(sb.toString());

        }catch (JSONException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }


    //private void addDrawerItems() {
        //String[] optionsArray = { "Microwaves", "Vending Machines", "Water Fountains", "", "About", "Give Feedback"};
        //mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, optionsArray);
        //mDrawerList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        //mDrawerList.setAdapter(mAdapter);
    //}

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Menu");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
