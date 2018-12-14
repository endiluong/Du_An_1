package com.example.admin.du_an_1.UI;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.du_an_1.DAO.daoUsers;
import com.example.admin.du_an_1.R;
import com.example.admin.du_an_1.Repository.Users;

import java.util.ArrayList;
import java.util.List;

public class ChangePWActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etNewPass,etReNewPass;
    TextView tvUserName;
    Button btnAccept,btnCancel;
    daoUsers dao_Users;
    List<Users> usersList = new ArrayList<>();
    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pw);
        tvUserName = (TextView) findViewById(R.id.tvUserChangePass);
        etNewPass = (EditText) findViewById(R.id.etNewPass);
        etReNewPass = (EditText) findViewById(R.id.etReNewPass);
        btnAccept = (Button) findViewById(R.id.btnAccept);
        btnCancel = (Button) findViewById(R.id.btnCancelChange);
        dao_Users = daoUsers.getInstance(this);

        userName= getIntent().getExtras().getString("userName");
        btnAccept.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        tvUserName.setText(userName);
    }

    public void Huy(View view){ finish(); }

    public int validateForm() {
        int check = 1;
        if (etNewPass.getText().length() == 0 || etReNewPass.getText().length() == 0) {

            Toast.makeText(getApplicationContext(), "\n" +
                    "Please enter full information ", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String pass = etNewPass.getText().toString();
            String rePass = etReNewPass.getText().toString();
            if (!pass.equals(rePass)) {
                Toast.makeText(getApplicationContext(), "Passwords do not match\n", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;

    }

    public void changePassword(String userName) {
        // SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        // String strUserName = pref.getString("USERNAME","");String strUserName = tvUserName.setText();




        dao_Users = new daoUsers(ChangePWActivity.this);
        Users user = new Users(userName, etNewPass.getText().toString());
        try {
                if (validateForm()>0){
                if (dao_Users.changePassWord(user) > 0) {
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAccept:
                changePassword(userName);
                break;
            case R.id.btnCancelChange:
                Huy(view);
                break;
        }
    }
}
