package com.example.admin.du_an_1.UI.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.du_an_1.Adapter.TabAdapter;
import com.example.admin.du_an_1.R;

public class Fragment_Statitic extends Fragment{

    TabAdapter myAdapter;

    Fragment_Stats fmStats;
    Fragment_ListStats fmListStats;
    Context context;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        context= getActivity();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_statitic,container,false);

        fmListStats = new Fragment_ListStats();
        fmStats = new Fragment_Stats();

        myAdapter = new TabAdapter(getChildFragmentManager());

        myAdapter.addFragment( fmListStats,"List Out" );
        myAdapter.addFragment( fmStats,"Statitics" );

        TabLayout tabItem = view.findViewById( R.id.tabItem );
        ViewPager vp = view.findViewById( R.id.vp );

        vp.setAdapter( myAdapter );
        tabItem.setupWithViewPager( vp );

        return view;
    }
}
