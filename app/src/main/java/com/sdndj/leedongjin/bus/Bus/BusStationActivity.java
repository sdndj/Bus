package com.sdndj.leedongjin.bus.Bus;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sdndj.leedongjin.bus.List.StationNameItem;
import com.sdndj.leedongjin.bus.List.Station_Adapter;
import com.sdndj.leedongjin.bus.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by LeeDongJin on 2016-08-22.
 */
public class BusStationActivity extends Activity implements AdapterView.OnItemClickListener {

    String BusID,arrmsg,name,myJSON;
    ListView station_name;

    ArrayList<StationNameItem> stationName = new ArrayList<StationNameItem>();

    private static final String TAG_ARRMSG1 = "arrmsg1";
    private static final String TAG_STATION = "station";
    private static final String TAG_SEQ = "seq";
    private static final String TAG_STATIONNM = "stationNm";
    private static final String TAG_RESULTLIST = "resultList";

    JSONArray busnumber = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.station_activity);

        Intent i = getIntent();
        BusID = i.getStringExtra("busRouteId"); //리스트에서 클릭한 버스 번호를 저장
        Log.d("BusRouteId: ", String.valueOf(BusID));

        station_name = (ListView) findViewById(R.id.station_name_listview);

        getData("http://m.bus.go.kr/mBus/bus/getLowRouteAndPos.bms?busRouteId=" + BusID);

        station_name.setOnItemClickListener(this);
    }

    private void getData(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream(), "Euc-kr"));


                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                myJSON = result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

    private void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            busnumber = jsonObj.getJSONArray(TAG_RESULTLIST); //resultlist 태그로 전체목록 긁어옴

            for (int i = 0; i < busnumber.length(); i++) { // 전체목록 수만큼 데이터를 ListView 에 추가시킴
                JSONObject c = busnumber.getJSONObject(i);
                String stationNm = c.getString(TAG_STATIONNM);
                String station = c.getString(TAG_STATION);
                String seq = c.getString(TAG_SEQ);

                stationName.add(new StationNameItem(stationNm, station, seq));
                Station_Adapter stationAdapter = new Station_Adapter(getLayoutInflater(), stationName);
                station_name.setAdapter(stationAdapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String station = stationName.get(position).getStation();
        String seq = stationName.get(position).getSeq();
        name = stationName.get(position).getstationNm();

        getArrmsg("http://m.bus.go.kr/mBus/bus/getLowArrInfoByRoute.bms?stId=" + station + "&busRouteId=" + BusID + "&ord=" + seq);
    }

    private void getArrmsg(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream(), "Euc-kr"));


                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                myJSON = result;
                showArrmsg();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

    //버스 오는 시간을 가져와 준다.
    private void showArrmsg() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            busnumber = jsonObj.getJSONArray(TAG_RESULTLIST); //resultlist 태그로 전체목록 긁어옴

            for (int i = 0; i < busnumber.length(); i++) { // 전체목록 수만큼 데이터를 ListView 에 추가시킴
                JSONObject c = busnumber.getJSONObject(i);
                arrmsg = c.getString(TAG_ARRMSG1);

                Intent intent = new Intent(this, Bus_time_Activity.class);
                intent.putExtra("name",name);
                intent.putExtra("arrmsg",arrmsg);
                startActivity(intent);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            stationName.clear();
        }
        return super.onKeyDown(keyCode, event);
    }
}