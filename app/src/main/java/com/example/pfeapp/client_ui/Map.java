package com.example.pfeapp.client_ui;

import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.pfeapp.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Map extends Fragment implements OnMapReadyCallback {


    private Button liste;
    private Button mapButton;
    Location currentLocation;



    private static  String MAPVIEW_BUNDLE_KEY;
    MapView map;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.map, container, false);
        map = view.findViewById(R.id.mapView);

        liste=(Button)view.findViewById(R.id.liste_Button);
        mapButton=(Button)view.findViewById(R.id.mapButton);



        ///////////////////////////////////////////////////

        liste.setBackground(getResources().getDrawable(R.drawable.map_button_left));
        liste.setTextColor(getResources().getColor(R.color.Navy));
        mapButton.setBackground(getResources().getDrawable(R.drawable.liste_button_left));
        mapButton.setTextColor(getResources().getColor(R.color.White));


        MAPVIEW_BUNDLE_KEY = getString(R.string.map_key);
        initGoogleMap(savedInstanceState);


        ///////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////
        liste=(Button)view.findViewById(R.id.liste_Button);

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


        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = map.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(getActivity(), R.raw.mapstayle));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }

        map.addMarker(new MarkerOptions().position(new LatLng(0,0)).title("Marker"));
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
