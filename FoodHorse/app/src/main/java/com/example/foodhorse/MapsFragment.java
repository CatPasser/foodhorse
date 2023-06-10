package com.example.foodhorse;

import static android.app.Activity.RESULT_OK;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

//import com.example.foodhorse.databinding.ActivityMapsBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsFragment extends Fragment implements ClusterManager.OnClusterItemInfoWindowClickListener{

    private static final int REQUEST_LOCATION_PERMISSION = 1;
    public GoogleMap mMap;
    //private ActivityMapsBinding binding;
    public static final int PERMISSIONS_REQUEST_LOCATION = 100;

    private Location myLocation;
    private Handler mHandler;
    private Runnable mRunnable;
    FusedLocationProviderClient fusedLocationProviderClient;
    private ClusterManager<MyItem> clusterManager;
    private final static int REQUEST_CODE = 100;
    private List<String> fullname = new ArrayList<>();
    private List<String> address = new ArrayList<>();
    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getActivity(),R.raw.map_style));
            MapsInitializer.initialize(getActivity());
            mMap = googleMap;
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                mMap = googleMap;
                mMap.setMyLocationEnabled(true);
            } else {
                askPermission();
            }
            setUpClusterer();
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        //binding = ActivityMapsBinding.inflate(getLayoutInflater());
        requestUserLocation();
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.mapFragment);
        if (mapFragment == null) {
            mapFragment = new SupportMapFragment();
            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
            ft.add(R.id.mapFragment, mapFragment, "mapFragment");
            ft.commit();
            getChildFragmentManager().executePendingTransactions();
        }
        mapFragment.getMapAsync(callback);

        return inflater.inflate(R.layout.fragment_maps, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapFragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }*/
    }

    public void requestUserLocation() {
        final LocationManager mLocation = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        //判斷當前是否已經獲得了定位權限
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            mLocation.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, new LocationListener() {
                @Override
                public void onLocationChanged(final Location location) {

                    final StringBuffer sb = new StringBuffer();
                    sb.append("Now location is : \n")
                            .append("lat : " + location.getLatitude()).append("\n")
                            .append("lng : " + location.getLongitude());


                    if (myLocation == null) {
                        myLocation = location;

                        // 移動地圖到我的位置
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 15));

                    }
                    myLocation = location;
                    if (mHandler == null) {
                        mHandler = new Handler();
                    }
                }
                @Override
                public void onStatusChanged(final String s, final int i, final Bundle bundle) {
                }

                @Override
                public void onProviderEnabled(final String s) {
                }

                public void onProviderDisabled(final String s) {
                }
            },  getActivity().getMainLooper());
        }
        else{
            askPermission();
        }
    }
    private List<Double> getLatlng(String address) {
        List<Double> latlng = new ArrayList<>();
        Geocoder geoCoder = new Geocoder(getActivity(), Locale.getDefault());
        List<Address> addressLocation = null;
        try {
            addressLocation = geoCoder.getFromLocationName(address, 1);
            latlng.add(addressLocation.get(0).getLatitude());
            latlng.add(addressLocation.get(0).getLongitude());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return latlng;
    }

    public void setUpClusterer() {
        //標記叢集
        clusterManager = new ClusterManager<MyItem>(getActivity(), mMap);
        clusterManager.setAnimation(false);
        mMap.setOnCameraIdleListener(clusterManager);
        //mMap.setOnMarkerClickListener(clusterManager);
        clusterManager.setOnClusterItemInfoWindowClickListener(this);


        addMarker();
    }

    @Override
    public void onClusterItemInfoWindowClick(ClusterItem item) {
        //點擊事件
        Intent intent = new Intent(getContext(),Driver_Shop_Order.class);
        intent.putExtra("address",item.getSnippet());
        intent.putExtra("name",item.getTitle());
        startActivityForResult(intent,123);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MainActivity mainActivity = (MainActivity) getActivity();
        if(resultCode == RESULT_OK)
        {
            mainActivity.OpenDriverOrder();
        }
        //mainActivity.OpenDriverOrder();

    }

    private void addMarker() {
        double lat, lng;
        FetchData fetchData = new FetchData("http://20.187.122.219/horse/map/shopMarker.php");
        if (fetchData.startFetch()) {
            if (fetchData.onComplete()) {
                String result = fetchData.getResult();
                if (!result.equals("No Data")) {
                    String array[] = result.split("&&");
                    for (int i = 0; i < array.length; i++) {
                        String array2[] = array[i].split(",");
                        this.fullname.add(array2[0]);
                        this.address.add(array2[1]);
                    }
                }
            }
        }
        for (int index = 0; index < address.size(); index++) {
            lat = getLatlng(address.get(index)).get(0);
            lng = getLatlng(address.get(index)).get(1);

            String title = fullname.get(index);
            String Location = address.get(index);

            MyItem Marker = new MyItem(lat, lng, title, Location);

            clusterManager.addItem(Marker);
        }

    }

    public void askPermission(){
        ActivityCompat.requestPermissions(getActivity(), new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION},PERMISSIONS_REQUEST_LOCATION);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}