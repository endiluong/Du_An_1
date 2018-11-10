package com.example.admin.du_an_1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.admin.du_an_1.Repository.SQLiteHelper;
import com.example.admin.du_an_1.Repository.Storage;

import java.util.ArrayList;

public class daoStorage {
    private SQLiteDatabase db;
    private static daoStorage instance;

    public daoStorage(Context context) {
        SQLiteHelper sqlHelper = new SQLiteHelper(context);
        db = sqlHelper.getWritableDatabase();
    }
    // GET ONE ITEM
    //
    //
    public ArrayList<Storage> getDataModels(String sql, String... selectionArgs) {
        ArrayList<Storage> result = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        Storage temp;
        while (c.moveToNext()) {
            temp = new Storage();
            temp.setId(c.getString(c.getColumnIndex(SQLiteHelper.STORAGE_ID)));
            temp.setProductName(c.getString(c.getColumnIndex(SQLiteHelper.STORAGE_PRODUCTNAME)));
            temp.setProductCode(c.getString(c.getColumnIndex(SQLiteHelper.STORAGE_PRODUCTCODE)));
            temp.setQuantity(c.getInt(c.getColumnIndex(SQLiteHelper.STORAGE_QUANTITY)));
            temp.setDate(c.getString(c.getColumnIndex(SQLiteHelper.STORAGE_DATE)));
            result.add(temp);
        }
        return result;
    }
    //Get All List
    public ArrayList<Storage> getAllItem() {
        String sql = "SELECT * FROM " + SQLiteHelper.TABLE_STORAGE_NAME;
        return getDataModels(sql);
    }
    //get By Id
    public Storage getById(String Id) {
        String sql = "SELECT * FROM " + SQLiteHelper.TABLE_STORAGE_NAME + " WHERE ID=? ";
        ArrayList<Storage> list = getDataModels(sql, Id);
        return list.get(0);
    }
    // Add
    public long insertStorage(Storage datamodel) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.STORAGE_ID, datamodel.getId());
        values.put(SQLiteHelper.STORAGE_PRODUCTNAME, datamodel.getProductName());
        values.put(SQLiteHelper.STORAGE_PRODUCTCODE, datamodel.getProductCode());
        values.put(SQLiteHelper.STORAGE_QUANTITY, datamodel.getQuantity());
        values.put(SQLiteHelper.STORAGE_DATE, datamodel.getDate());
        return db.insert(SQLiteHelper.TABLE_STORAGE_NAME, null, values);
    }
    //Update
        public int updateStorage(Storage datamodel) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.STORAGE_ID, datamodel.getId());
        values.put(SQLiteHelper.STORAGE_PRODUCTNAME, datamodel.getProductName());
        values.put(SQLiteHelper.STORAGE_PRODUCTCODE, datamodel.getProductCode());
        values.put(SQLiteHelper.STORAGE_QUANTITY, datamodel.getQuantity());
        values.put(SQLiteHelper.STORAGE_DATE, datamodel.getDate());
        return db.update(SQLiteHelper.TABLE_STORAGE_NAME, values, "id=?", new String[]{String.valueOf(datamodel.getId())});
    }
    //Delete by Id
    public int deleteStorage(int id) {
        return db.delete(SQLiteHelper.TABLE_STORAGE_NAME, "id=?", new String[]{String.valueOf(id)});
    }
    ///////////////////////////////////////////////////
    // CREATE INSTANCE
    public static daoStorage getInstance(Context context) {
        if (instance == null) {
            instance = new daoStorage(context);
        }
        return instance;
    }

}
