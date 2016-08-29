package com.sdndj.leedongjin.bus.Bus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sdndj.leedongjin.bus.R;

/**
 * Created by LeeDongJin on 2016-08-29.
 */
public class Bus_time_Activity extends FragmentActivity implements OnMapReadyCallback{

    TextView st_name,st_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager() //map 프레그먼트 호출
                .findFragmentById(R.id.bus_map);
        mapFragment.getMapAsync(this);
        Intent i = getIntent();
        st_name = (TextView) findViewById(R.id.bus_st_name);
        st_name.setText(i.getStringExtra("name"));
        st_info = (TextView) findViewById(R.id.bus_time);
        st_info.setText(i.getStringExtra("arrmsg"));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng Position1 = new LatLng(37.5511572043,126.9904431246); //자신의 위치를 저장 y,x

        googleMap.addMarker(new MarkerOptions().position(Position1).title("남산서울타워"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5522426268, 126.9815549995)).title("남산도서관"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Position1)); //맵 시작 시 myPosition으로 카메라 이동
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Position1, 17));
    }
}
