package com.example.admin.du_an_1.Controller;

import android.content.Context;
import android.widget.Toast;

import com.example.admin.du_an_1.DAO.daoUsers;
import com.example.admin.du_an_1.Repository.Users;

import java.util.ArrayList;
import java.util.List;

public class SignUpService {
    Context context;
    List<Users> usersList;
    daoUsers myDAO;

    public SignUpService(Context context) {
        this.context = context;
        myDAO= daoUsers.getInstance(context);
    }

    public boolean SignUp(String username, String password){
        if(Validator(username,password)){
            //CREATE USER
            Users temp = new Users(username,password);
            myDAO.insertUser(temp);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean Validator(String username, String password){
        // GET THE USER LIST FROM DATABASE TO CHECK FOR DUPLICATE
        usersList= new ArrayList<>();
        usersList= myDAO.getAllItem();
        //
        // Create temporary User
        Users user = new Users(username,password);
        //
        // Check user list for duplicate username
        //
        for (int i = 0; i < usersList.size(); i++) {
            if ((user.getUserName()).equals(usersList.get(i).getUserName())) {
                Toast.makeText(context, "Duplicate Username, please use another username", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }


}
