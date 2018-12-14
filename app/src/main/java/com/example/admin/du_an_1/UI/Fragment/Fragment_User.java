package com.example.admin.du_an_1.UI.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.du_an_1.R;
import com.example.admin.du_an_1.UI.ChangePWActivity;
import com.example.admin.du_an_1.UI.LoginActivity;
import com.example.admin.du_an_1.UI.UserActivity;

import org.w3c.dom.Text;

public class Fragment_User extends Fragment implements View.OnClickListener {
    Boolean isAdmin;
    Button btnLogout, btnViewAll, btnChangepw;
    String userName = "";
    TextView tvUserName;
    Context context;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isAdmin = getArguments().getBoolean("isAdmin");
        userName = getArguments().getString("userName");
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        tvUserName = view.findViewById(R.id.tvUsernameShow);
        btnLogout = view.findViewById(R.id.btnLogOut);
        btnChangepw = view.findViewById(R.id.btnChange);
        btnViewAll = view.findViewById(R.id.btnViewUserList);

        if (isAdmin) {
            tvUserName.setText("Admin");
        } else {
            tvUserName.setText(userName.toString());
        }
        btnViewAll.setOnClickListener(this);
        btnChangepw.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnChange:
                if (isAdmin) {
                    Toast.makeText(context, "Admin Account cant be change password", Toast.LENGTH_SHORT).show();
                } else {
                    // Do change password
                    Intent i= new Intent(getContext(), ChangePWActivity.class);
                    i.putExtra("userName", userName.toString());
                    startActivity(i);
                    // startActivity(new Intent(getContext(),ChangePWActivity.class));
                }
                break;
            case R.id.btnViewUserList:
                if (!isAdmin) {
                    Toast.makeText(context, "Only Admin can access this feature", Toast.LENGTH_SHORT).show();
                } else {
                    if (isAdmin){
                        startActivity(new Intent(getContext(),UserActivity.class));}
                }
                break;
            case R.id.btnLogOut:
                // Log out
                startActivity(new Intent(getContext(),LoginActivity.class));
                break;
        }
    }


}
