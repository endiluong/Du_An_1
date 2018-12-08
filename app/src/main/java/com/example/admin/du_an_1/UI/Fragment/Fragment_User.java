package com.example.admin.du_an_1.UI.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.du_an_1.R;

import org.w3c.dom.Text;

public class Fragment_User extends Fragment {
    Boolean isAdmin;
    String userName="";
    TextView tvUserName;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isAdmin= getArguments().getBoolean("isAdmin");
        userName= getArguments().getString("userName");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_user,container,false);
        tvUserName= view. findViewById(R.id.tvUsernameShow);
        if(isAdmin){tvUserName.setText("Admin");} else { tvUserName.setText(userName.toString());}



        return view;
    }
}
