package com.example.pfeapp.client_ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.pfeapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;

public class Map extends Fragment implements OnMapReadyCallback {

    LatLng alger = new LatLng(36.7762, 3.05997);
    private MaterialButton liste;
    private MaterialButton mapButton;

    private static  String MAPVIEW_BUNDLE_KEY;
    MapView map;
    GoogleMap gmap;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.map, container, false);
        map = view.findViewById(R.id.mapView);

        liste=(MaterialButton)view.findViewById(R.id.liste_Button);
        mapButton=(MaterialButton)view.findViewById(R.id.mapButton);




        ///////////////////////////////////////////////////

        liste.setBackgroundColor(getResources().getColor(R.color.White));
        liste.setTextColor(getResources().getColor(R.color.Navy));
        mapButton.setBackgroundColor(getResources().getColor(R.color.Navy));
        mapButton.setTextColor(getResources().getColor(R.color.White));


        MAPVIEW_BUNDLE_KEY = getString(R.string.map_key);
        initGoogleMap(savedInstanceState);


        ///////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////
        liste=(MaterialButton)view.findViewById(R.id.liste_Button);

        liste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fr= getFragmentManager().beginTransaction();
                fr.replace(R.id.Fragment_container,new ResRech());
                fr.commit();




            }
        });





        return view;
    }


    private void initGoogleMap(Bundle savedInstanceState){
        // *** IMPORTANT ***
        // MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
        // objects or sub-Bundles.
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }

        map.onCreate(mapViewBundle);

        map.getMapAsync(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        map.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        map.onStop();
    }

    @Override
    public void onMapReady(GoogleMap map) {

        map.addMarker(new MarkerOptions().position(new LatLng(36.7762, 3.05997)).title("Restaurant 1"));
        map.addMarker(new MarkerOptions().position(new LatLng(36.7762, 3.04997)).title("Restaurant 2"));
        map.addMarker(new MarkerOptions().position(new LatLng(36.7762, 3.03997)).title("Restaurant 3"));
        map.addMarker(new MarkerOptions().position(new LatLng(36.7782, 3.03597)).title("Restaurant 4"));
        map.addMarker(new MarkerOptions().position(new LatLng(36.7772, 3.03697)).title("Restaurant 5"));
        map.addMarker(new MarkerOptions().position(new LatLng(36.7712, 3.02997)).title("Restaurant 6"));
        map.addMarker(new MarkerOptions().position(new LatLng(36.7702, 3.04997)).title("Restaurant 7"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(alger, 13));

    }

    @Override
    public void onPause() {
        map.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        map.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        map.onLowMemory();
    }
}
