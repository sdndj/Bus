package com.sdndj.leedongjin.bus.Bus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.sdndj.leedongjin.bus.List.BusLineListItem;
import com.sdndj.leedongjin.bus.List.BusLine_Adapter;
import com.sdndj.leedongjin.bus.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by LeeDongJin on 2016-08-22.
 */
public class BusNumber extends Fragment implements AdapterView.OnItemClickListener {

    private ListView BusList;
    private EditText etSearch;

    ArrayList<BusLineListItem> busLineList = new ArrayList<BusLineListItem>();
    BusLine_Adapter busLine_adapter;
    JSONArray busnumber = null;
    private Handler handler;
    private ProgressDialog progressDialog;

    String myJSON;
    private static final String TAG_BUSROUTENM = "busRouteNm";
    private static final String TAG_BUSROUTEID = "busRouteId";
    private static final String TAG_RESULTLIST = "resultList";



    public static BusNumber newbusLine() {
        BusNumber busNumber = new BusNumber();
        return busNumber;
    }

    public BusNumber() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.busline, container, false);

        Loading();
        getData("http://m.bus.go.kr/mBus/bus/getLowBusRoute.bms?strSrch:");  //getData로 주소를 전달

        BusList = (ListView) v.findViewById(R.id.bus_line_listview);
        etSearch = (EditText) v.findViewById(R.id.search_edt);
        BusList.setOnItemClickListener(this);
        BusList.setTextFilterEnabled(true);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                busLine_adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return v;
    }

    private void Loading() {
        handler = new android.os.Handler();

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog = ProgressDialog.show(getActivity(), "", "버스 정보 불러오는 중", true);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (progressDialog != null && progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 4000);
            }
        });
    }

    //데이터를 읽어오는 메소드
    public void getData(String url) {
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

    //태그를 이용하여 json으로 긁어옴
    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            busnumber = jsonObj.getJSONArray(TAG_RESULTLIST); //results 태그로 전체목록 긁어옴

            for (int i = 0; i < busnumber.length(); i++) { // 전체목록 수만큼 데이터를 ListView 에 추가시킴
                JSONObject c = busnumber.getJSONObject(i);
                String bus_number = c.getString(TAG_BUSROUTENM);
                String bus_id = c.getString(TAG_BUSROUTEID);

                busLineList.add(new BusLineListItem(bus_number,bus_id));
                busLine_adapter = new BusLine_Adapter(getLayoutInflater(Bundle.EMPTY),busLineList);
                BusList.setAdapter(busLine_adapter);
            }
            Collections.sort(busLineList, new Comparator<BusLineListItem>() {
                @Override
                public int compare(BusLineListItem lhs, BusLineListItem rhs) {
                    return lhs.getBusNm().compareTo(rhs.getBusNm());
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(getActivity(), BusStationActivity.class);
        i.putExtra("busRouteId", busLineList.get(position).getBusId());
        startActivity(i);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        busLineList.clear();
    }
}
