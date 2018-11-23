package com.example.admin.du_an_1.Controller;

import android.content.Context;
import android.widget.Toast;

import com.example.admin.du_an_1.DAO.daoUsers;
import com.example.admin.du_an_1.Repository.Users;

import java.util.ArrayList;
import java.util.List;

public class LoginService {
    Context context;
    List<Users> usersList;
    daoUsers myDAO;

    public LoginService(Context context) {
        this.context = context;
        myDAO= daoUsers.getInstance(context);
    }


    public boolean Login(String username, String password) {
        if (adminLogin(username, password)) {
            return true;
        }
        return false;
    }

    private boolean adminLogin(String username, String password) {
        if ((username.equals("admin")) && (password.equals("admin"))) {
            Toast.makeText(context, "Login as Admin", Toast.LENGTH_SHORT).show();
            return true;
        }
        return userLogin(username, password);
    }

    private boolean userLogin(String username, String password) {
        //get uset list
        usersList = new ArrayList<>();
        usersList = myDAO.getAllItem();

        Users temp = new Users(username, password);

        for (int i = 0; i < usersList.size(); i++) {
            if ((temp.getUserName()).equals(usersList.get(i).getUserName()) && (temp.getPassword()).equals(usersList.get(i).getPassword())) {
                Toast.makeText(context, "Login as" + username, Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return false;
    }
}