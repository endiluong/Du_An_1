package com.example.admin.du_an_1.Controller;

import android.content.Context;
import android.widget.Toast;

public class LoginService {
    Context context;

    public LoginService(Context context) {
        this.context = context;
    }


    public boolean Login(String username,String password){
        if(adminLogin(username,password)){
            return true;
        }
        return false;
    }
    private boolean adminLogin(String username,String password){
        if((username.equals("admin")) &&( password.equals("admin"))){
            Toast.makeText(context, "Login as Admin", Toast.LENGTH_SHORT).show();
            return true;
        }
        return userLogin(username,password);
    }
    private boolean userLogin(String username,String password){
        if((username.equals("user")) &&( password.equals("user"))){
            Toast.makeText(context, "Login as User", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
