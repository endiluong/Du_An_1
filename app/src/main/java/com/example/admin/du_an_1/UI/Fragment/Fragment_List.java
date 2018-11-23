package com.example.admin.du_an_1.UI.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.admin.du_an_1.R;
import com.example.admin.du_an_1.UI.AddTicketActivity;

public class Fragment_List extends Fragment implements View.OnClickListener {
    FloatingActionButton fab;
    ListView productList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_list, container, false);
        fab= (FloatingActionButton) view.findViewById(R.id.btnFAB_Add);
        productList= (ListView) view.findViewById(R.id.lv_product);
        fab.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getActivity(), AddTicketActivity.class));
    }
}
