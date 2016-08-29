package com.sdndj.leedongjin.bus.Addons;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdndj.leedongjin.bus.R;

/**
 * Created by LeeDongJin on 2016-08-20.
 */
public class Favorites extends Fragment{

    public static Favorites newInFavoritesActivity(){
        Favorites fragment = new Favorites();
        return fragment;
    }

    public Favorites(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.favorites_tab, container, false);
    }
}
