package com.sdndj.leedongjin.bus.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sdndj.leedongjin.bus.R;

import java.util.ArrayList;

/**
 * Created by LeeDongJin on 2016-08-23.
 */
public class Station_Adapter extends BaseAdapter{
    ArrayList<StationNameItem> items;

    LayoutInflater inflater;

    public Station_Adapter(LayoutInflater inflater, ArrayList<StationNameItem> items){
        this.items = items;
        this.inflater = inflater;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = inflater.inflate(R.layout.station_listitem,null);
        }
        TextView text_stationNm = (TextView) convertView.findViewById(R.id.station_name_textview);

        text_stationNm.setText(items.get(position).getstationNm());
        return convertView;
    }
}
