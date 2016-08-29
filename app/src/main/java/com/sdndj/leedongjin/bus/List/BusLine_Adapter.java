package com.sdndj.leedongjin.bus.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.sdndj.leedongjin.bus.R;

import java.util.ArrayList;

/**
 * Created by LeeDongJin on 2016-08-23.
 */
public class BusLine_Adapter extends BaseAdapter {

    ArrayList<BusLineListItem> items;
    private ArrayList<BusLineListItem> mStringFilterList;
    LayoutInflater inflater;

    private ValueFilter valueFilter;

    public BusLine_Adapter(LayoutInflater inflater, ArrayList<BusLineListItem> items) {
        this.items = items;
        this.mStringFilterList = items;
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
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.busline_listitem, null);
        }
        TextView text_busNm = (TextView) convertView.findViewById(R.id.busNm);
        TextView text_busId = (TextView) convertView.findViewById(R.id.busId);

        text_busNm.setText(items.get(position).getBusNm());
        text_busId.setText(items.get(position).getBusId());
        return convertView;
    }

    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = mStringFilterList;
                results.count = mStringFilterList.size();
            } else {
                ArrayList<BusLineListItem> filterDataList = new ArrayList<BusLineListItem>();
                filterDataList.clear();
                for (BusLineListItem data : items) {
                    if (data.getBusNm().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        filterDataList.add(data);
                }
                results.values = filterDataList;
                results.count = filterDataList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                items = (ArrayList<BusLineListItem>) results.values;
                notifyDataSetChanged();
                for (int i = 0; i < items.size(); i++) {
                }
            }
        }
    }
}
