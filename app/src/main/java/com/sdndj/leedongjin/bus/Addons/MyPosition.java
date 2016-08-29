package com.sdndj.leedongjin.bus.Addons;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.sdndj.leedongjin.bus.R;

/**
 * Created by LeeDongJin on 2016-08-20.
 */
public class MyPosition extends Fragment implements View.OnClickListener {

    Button position,map; //자기위치얻어오기, 지도로 이동
    TextView gps_text;
    ListView listView; //주변 정류장 리스트뷰
    ArrayAdapter<String> adapter;

    private GpsInfo gps;

    public static MyPosition newmyPosition() {
        MyPosition myPosition = new MyPosition();
        return myPosition;
    }

    public MyPosition() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.position, container, false);
        position = (Button) v.findViewById(R.id.my_position);
        map = (Button) v.findViewById(R.id.see_map);
        gps_text = (TextView) v.findViewById(R.id.text_position);
        listView = (ListView) v.findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        adapter.add("현재 위치에서 주변에 있는 정류장을 불러옵니다.");

        position.setOnClickListener(this);
        map.setOnClickListener(this);
        getGps();
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_position:
                getGps();
                break;
            case R.id.see_map:
                startActivity(new Intent(getActivity(), MapsActivity.class));
                break;
        }
    }

    private void getGps() {
        gps = new GpsInfo(getActivity());

        if (gps.isGetLocation()){
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            gps_text.setText("내 위치: "+latitude+", "+longitude);
        }else{
            gps.showSettingsAlert();
        }
    }
}
