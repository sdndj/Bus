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
public class Bus_time_Activity extends FragmentActivity implements OnMapReadyCallback {

    TextView st_name, st_info;

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

        LatLng Position1 = new LatLng(37.5511572043, 126.9904431246); //자신의 위치를 저장 y,x

        /***********************02 번 정류장*******************/
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5511572043, 126.9904431246)).title("남산서울타워 (02번)(05번)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5522426268, 126.9815549995)).title("남산도서관 (02번)(05번)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5545553981, 126.980237787)).title("교육연구정보원 (02번)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5552850097, 126.9838858506)).title("남산케이블카.남산산책로입구 (02번)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5586203825, 126.9874059964)).title("서울애니메이션센터숭의여대 (02번)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5609899926, 126.992490506)).title("퇴계로3가.한옥마을.대한극장앞 (02번)(05번)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5613196823, 126.9948830406)).title("충무로역2번출구.대한극장앞 (02번)(05번)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5624801112, 126.9999300049)).title("퇴계로5가.제일병원 (02번)(05번)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5601814056, 127.0027220126)).title("앰배서더호텔.장충문화체육센터 (02번)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5581192384, 127.0054329618)).title("동대입구역.장충동 (02번)(05번)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5516156456, 127.0010413652)).title("국립극장 (02번)(05번)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5506954106, 126.998808215)).title("남산북측순환로입구 (02번)(05번)"));
        /******************************************************/

        /***********************05 번 정류장*******************/
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5556147024, 126.9801102008)).title("백범광장 (05번)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5564311471, 126.97667105)).title("힐튼호텔 (05번)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.558573168, 126.9759991814)).title("남대문시장악세사리전문상가 (05번)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5613289437, 126.9800196252)).title("남대문시장 (05번)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5607673404, 126.985225811)).title("명동입구 (05번)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5634272623, 127.003927575)).title("퇴계로6가 (05번)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5644650619, 127.0094021679)).title("광희문.광희동사거리 (05번)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5651214487, 127.0147221363)).title("충무아트센터.스포츠센터.중부소방서 (05번)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5627493254, 127.0151420099)).title("신당동떡볶이타운.중구여성플라자 (05번)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5592387162, 127.0130812274)).title("신당동.청구역 (05번)"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.5559696134, 127.0111792179)).title("약수역2번출구.중구구립도서관 (05번)"));
        /******************************************************/

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Position1)); //맵 시작 시 position으로 카메라 이동
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Position1, 15));
    }
}
