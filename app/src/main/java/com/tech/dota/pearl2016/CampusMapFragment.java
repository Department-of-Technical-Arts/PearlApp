package com.tech.dota.pearl2016;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import app.ControllerConstants;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CampusMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CampusMapFragment extends Fragment implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public CampusMapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CampusMapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CampusMapFragment newInstance(String param1, String param2) {
        CampusMapFragment fragment = new CampusMapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        return inflater.inflate(R.layout.fragment_campus_map, container, false);
    }

    SearchBox search;
    GoogleMap mMap;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        search = (SearchBox) view.findViewById(R.id.searchbox);
        for(int x = 0; x < ControllerConstants.names.length; x++){
            SearchResult option = new SearchResult(R.drawable.ic_directions, ControllerConstants.names[x]);
            search.addSearchable(option);
        }
        search.setLogoText("Lost Somewhere? ");
/*        search.setMenuListener(new SearchBox.MenuListener(){
            @Override
            public void onMenuClick() {

            }
        });*/
        search.setDrawerLogo(R.drawable.ic_directions);
        search.setLogoTextColor(Color.GRAY);
        search.setSearchListener(new SearchBox.SearchListener(){

            @Override
            public void onSearchOpened() {
                //Use this to tint the screen
                search.setHint("Type here for suggestions...");
            }

            @Override
            public void onSearchClosed() {
                //Use this to un-tint the screen
            }

            @Override
            public void onSearchTermChanged(String s) {

            }

            @Override
            public void onSearch(String searchTerm) {
                goToTheLocation(searchTerm);
            }

            @Override
            public void onResultClick(SearchResult result){
                //React to a result being clicked
                goToTheLocation(search);
            }

            @Override
            public void onSearchCleared() {

            }
        });
    }

    private void goToTheLocation(String searchTerm) {
        searchTerm = searchTerm.toLowerCase();
        LatLng latLng = null;
        for (int i = 0; i < ControllerConstants.names.length; i++) {
            if (searchTerm.equals(ControllerConstants.names[i].toLowerCase())) {
                latLng = new LatLng(ControllerConstants.latitudes[i], ControllerConstants.longitudes[i]);
            }
        }
        if(latLng!=null) {
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLng)
                    .zoom(20)
                    .bearing(-90)
                    .tilt(60)
                    .build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
            mMap.animateCamera(cameraUpdate, 1000, null);
        }else{
            Toast.makeText(getContext(),searchTerm+" not found",Toast.LENGTH_SHORT).show();
        }
    }

    private void goToTheLocation(SearchBox search) {
        String s= search.getSearchText();
        Log.e("search",s);
        s=s.toLowerCase();
        LatLng latLng =null;
        for(int i=0;i<ControllerConstants.names.length;i++){
            if(s.equals(ControllerConstants.names[i].toLowerCase())){
                latLng=new LatLng(ControllerConstants.latitudes[i],ControllerConstants.longitudes[i]);
            }
        }
        if (latLng!=null) {
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLng)
                    .zoom(20)
                    .bearing(-90)
                    .tilt(60)
                    .build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
            mMap.animateCamera(cameraUpdate, 1000, null);
        }else{
            Toast.makeText(getContext(),s+"not found",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        IconGenerator factory = new IconGenerator(getContext());
        factory.setStyle(factory.STYLE_BLUE);
        mMap = googleMap;

        for (int i = 0; i < ControllerConstants.names.length; i++) {
            Bitmap icon = factory.makeIcon(ControllerConstants.names[i]);
            LatLng location = new LatLng(ControllerConstants.latitudes[i], ControllerConstants.longitudes[i]);
            mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(icon)).position(location));
        }

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(ControllerConstants.latitudes[0], ControllerConstants.longitudes[0]))
                .zoom(18)
                .bearing(180)
                .tilt(60)
                .build();
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public static final String PREFS_NAME = "MyPrefsFile1";
    public CheckBox dontShowAgain;

    @Override
    public void onAttach(Context context) {

        AlertDialog.Builder adb = new AlertDialog.Builder(getContext());
        LayoutInflater adbInflater = LayoutInflater.from(getContext());
        View eulaLayout = adbInflater.inflate(R.layout.dontshowdialog, null);
        dontShowAgain = (CheckBox) eulaLayout.findViewById(R.id.skip);
        adb.setView(eulaLayout);
        adb.setTitle("Get Directions");
        adb.setIcon(R.drawable.ic_information);
        adb.setMessage("Tap marker to avail options at bottom right corner.");
        adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String checkBoxResult = "NOT checked";
                if (dontShowAgain.isChecked())
                    checkBoxResult = "checked";
                SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("skipMessage", checkBoxResult);
                // Commit the edits!
                editor.apply();
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://misha.beshkin.lv/android-alertdialog-with-checkbox/")));
                dialog.dismiss();
//                return;
            }
        });

        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        String skipMessage = settings.getString("skipMessage", "NOT checked");
        if (!skipMessage.equals("checked"))
            adb.show();
        super.onAttach(context);

    }
}
