package com.example.admin.du_an_1.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.du_an_1.Controller.SignUpService;
import com.example.admin.du_an_1.R;

public class AddUserActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etAddUser,etAddPass;
    Button btnAddUser,btnCancel;
    SignUpService signUpService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        etAddUser = (EditText) findViewById(R.id.edAddUser);
        etAddPass = (EditText) findViewById(R.id.edAddPass);
        btnAddUser = (Button) findViewById(R.id.btnAddUser);
        btnCancel = (Button)findViewById(R.id.btnAddCancel);
        signUpService = new SignUpService(this);

        btnAddUser.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

    }
    public void Cancel(View view){ finish(); }
    public void AddUser(String username,String password){
        if (validate()>0){
            if (this.signUpService.SignUp(username, password)){
                Toast.makeText(this, username+" was add success", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Add Fail", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    public int validate(){
        int check = 1;
        if (etAddUser.getText().length()==0||etAddPass.getText().length()==0){
            Toast.makeText(this, "Please enter full information", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddUser:
                AddUser(etAddUser.getText().toString(),etAddPass.getText().toString());
                break;
            case R.id.btnAddCancel:
                Cancel(view);
                break;
        }
    }
}
