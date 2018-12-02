package com.example.admin.du_an_1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.admin.du_an_1.Repository.SQLiteHelper;
import com.example.admin.du_an_1.Repository.Category;

import java.util.ArrayList;

import static com.example.admin.du_an_1.Repository.SQLiteHelper.CATEGORY_ID;

public class daoCategory {
    private SQLiteDatabase db;
    private static daoCategory instance;

    public daoCategory(Context context) {
        SQLiteHelper sqlHelper = new SQLiteHelper(context);
        db = sqlHelper.getWritableDatabase();
    }
    // GET ONE ITEM
    //
    //
    public ArrayList<Category> getDataModels(String sql, String... selectionArgs) {
        ArrayList<Category> result = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        Category temp;
        while (c.moveToNext()) {
            temp = new Category();
            temp.setId(c.getString(c.getColumnIndex(SQLiteHelper.CATEGORY_ID)));
            temp.setName(c.getString(c.getColumnIndex(SQLiteHelper.CATEGORY_NAME)));
            result.add(temp);
        }
        return result;
    }
    //Get All List
    public ArrayList<Category> getAllItem() {
        String sql = "SELECT * FROM " + SQLiteHelper.TABLE_CATEGORY_NAME;
        return getDataModels(sql);
    }
    //get By Id
    public Category getById(String Id) {
        String sql = "SELECT * FROM " + SQLiteHelper.TABLE_CATEGORY_NAME + " WHERE ID=? ";
        ArrayList<Category> list = getDataModels(sql, Id);
        return list.get(0);
    }
    // Add
    public long insertCat(Category datamodel) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.CATEGORY_NAME, datamodel.getName());

        return db.insert(SQLiteHelper.TABLE_CATEGORY_NAME, null, values);
    }
    //Update
    public int updateCat(Category datamodel) {
        ContentValues values = new ContentValues();
        values.put( CATEGORY_ID, datamodel.getId());
        values.put(SQLiteHelper.CATEGORY_NAME, datamodel.getName());
        return db.update(SQLiteHelper.TABLE_CATEGORY_NAME, values, "Id=?", new String[]{String.valueOf(datamodel.getId())});
    }

     ////////////////////////////////////
    //chỉnh sửa lại int ==> Category. //
    //Delete by Id
//    public int deleteCat(int id) {
//        return db.delete(SQLiteHelper.TABLE_CATEGORY_NAME, "Id=?", new String[]{String.valueOf(id)});
//    }
    public int deleteCat(Category category) {
        return db.delete(SQLiteHelper.TABLE_CATEGORY_NAME, "Id=?", new String[]{String.valueOf(category.getId())});
    }
    ///////////////////////////////////////////////////
    // CREATE INSTANCE
    public static daoCategory getInstance(Context context) {
        if (instance == null) {
            instance = new daoCategory(context);
        }
        return instance;
    }

}
