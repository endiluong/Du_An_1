package com.example.admin.du_an_1.Controller;

import android.content.Context;
import android.widget.Toast;

import com.example.admin.du_an_1.DAO.daoCategory;
import com.example.admin.du_an_1.Repository.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    Context context;
    Category cat;
    daoCategory myDAO;

    Category Cat;

    List<Category> lsCat;

    public CategoryService(Context context) {
        this.context = context;
        myDAO = daoCategory.getInstance( context );
    }

    public void delCat(int index){

        // Do Delete
        if (lsCat != null) {
            myDAO.deleteCat( index );
        }
    }

    public Boolean updateCategory(String Name){
        if (Name.isEmpty()){
            Toast.makeText( context,"Null",Toast.LENGTH_SHORT ).show();
        }
        else if(checkValid(Name)){
            //CREATE USER
            Category temp = new Category();
            temp.setName( Name );
            myDAO.updateCat(temp);
            return true;
        } else {
            return false;
        }

        return false;
    }

    public Boolean addCategory(String Name){
        if (Name.isEmpty()){
            Toast.makeText( context,"Null",Toast.LENGTH_SHORT ).show();
        }
        else if(checkValid(Name)){
            //CREATE USER
            Category temp = new Category();
            temp.setName( Name );
            myDAO.insertCat(temp);
            return true;
        } else {
            return false;
        }
        return false;
    }

    public Boolean checkValid(String Name){
        lsCat= new ArrayList<>();
        lsCat= myDAO.getAllItem();
//        Category cat = new Category();
        for (int i = 0; i < lsCat.size(); i++) {
            if (Name.equals(lsCat.get(i).getName())) {
                Toast.makeText(context, "FAIL", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
}
