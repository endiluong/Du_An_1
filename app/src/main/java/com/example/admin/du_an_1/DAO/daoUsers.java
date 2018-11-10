package com.example.admin.du_an_1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.admin.du_an_1.Repository.SQLiteHelper;
import com.example.admin.du_an_1.Repository.Users;

import java.util.ArrayList;

public class daoUsers {
    private SQLiteDatabase db;
    private static daoUsers instance;

    public daoUsers(Context context) {
        SQLiteHelper sqlHelper = new SQLiteHelper(context);
        db = sqlHelper.getWritableDatabase();
    }
    // GET ONE ITEM
    //
    //
    public ArrayList<Users> getDataModels(String sql, String... selectionArgs) {
        ArrayList<Users> result = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        Users temp;
        while (c.moveToNext()) {
            temp = new Users();
            temp.setId(c.getString(c.getColumnIndex(SQLiteHelper.USER_ID)));
            temp.setUserName(c.getString(c.getColumnIndex(SQLiteHelper.USER_UN)));
            temp.setPassword(c.getString(c.getColumnIndex(SQLiteHelper.USER_PW)));
            result.add(temp);
        }
        return result;
    }
    //Get All List
    public ArrayList<Users> getAllItem() {
        String sql = "SELECT * FROM " + SQLiteHelper.TABLE_USER_NAME;
        return getDataModels(sql);
    }
    //get By Id
    public Users getById(String Id) {
        String sql = "SELECT * FROM " + SQLiteHelper.TABLE_USER_NAME + " WHERE ID=? ";
        ArrayList<Users> list = getDataModels(sql, Id);
        return list.get(0);
    }
    // Add
    public long insertUser(Users datamodel) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.USER_ID, datamodel.getId());
        values.put(SQLiteHelper.USER_UN, datamodel.getUserName());
        values.put(SQLiteHelper.USER_PW, datamodel.getPassword());

        return db.insert(SQLiteHelper.TABLE_USER_NAME, null, values);
    }
    //Update
    public int updateUser(Users datamodel) {
        ContentValues values = new ContentValues();

        values.put(SQLiteHelper.USER_ID, datamodel.getId());
        values.put(SQLiteHelper.USER_UN, datamodel.getUserName());
        values.put(SQLiteHelper.USER_PW, datamodel.getPassword());
        return db.update(SQLiteHelper.TABLE_USER_NAME, values, "id=?", new String[]{String.valueOf(datamodel.getId())});
    }
    //Delete by Id
    public int deleteUser(int id) {
        return db.delete(SQLiteHelper.TABLE_USER_NAME, "id=?", new String[]{String.valueOf(id)});
    }
    ///////////////////////////////////////////////////
    // CREATE INSTANCE
    public static daoUsers getInstance(Context context) {
        if (instance == null) {
            instance = new daoUsers(context);
        }
        return instance;
    }

}
