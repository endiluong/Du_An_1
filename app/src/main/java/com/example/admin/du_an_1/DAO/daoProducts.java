package com.example.admin.du_an_1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.admin.du_an_1.Repository.SQLiteHelper;
import com.example.admin.du_an_1.Repository.Product;

import java.util.ArrayList;

public class daoProducts {
    private SQLiteDatabase db;
    private static daoProducts instance;

    public daoProducts(Context context) {
        SQLiteHelper sqlHelper = new SQLiteHelper(context);
        db = sqlHelper.getWritableDatabase();
    }
    // GET ONE ITEM
    //
    //
    public ArrayList<Product> getDataModels(String sql, String... selectionArgs) {
        ArrayList<Product> result = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        Product temp;
        while (c.moveToNext()) {
            temp = new Product();
            temp.setId(c.getString(c.getColumnIndex(SQLiteHelper.PRODUCT_ID)));
            temp.setName(c.getString(c.getColumnIndex(SQLiteHelper.PRODUCT_NAME)));
            temp.setCategory(c.getString(c.getColumnIndex(SQLiteHelper.PRODUCT_CATEGORY)));
            temp.setQuantity(Integer.parseInt(c.getString(c.getColumnIndex(SQLiteHelper.PRODUCT_QUANTITY))));
            temp.setCode(c.getString(c.getColumnIndex(SQLiteHelper.PRODUCT_CODE)));
            result.add(temp);
        }
        return result;
    }
    //Get All List
    public ArrayList<Product> getAllItem() {
        String sql = "SELECT * FROM " + SQLiteHelper.TABLE_PRODUCT_NAME;
        return getDataModels(sql);
    }
    //get By Id
    public Product getById(String Id) {
        String sql = "SELECT * FROM " + SQLiteHelper.TABLE_PRODUCT_NAME + " WHERE ID=? ";
        ArrayList<Product> list = getDataModels(sql, Id);
        return list.get(0);
    }
    // Add
    public long insertProduct(Product datamodel) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.PRODUCT_ID, datamodel.getId());
        values.put(SQLiteHelper.PRODUCT_NAME, datamodel.getName());
        values.put(SQLiteHelper.PRODUCT_CATEGORY, datamodel.getCategory());
        values.put(SQLiteHelper.PRODUCT_QUANTITY, datamodel.getQuantity());
        values.put(SQLiteHelper.PRODUCT_CODE, datamodel.getCode());
        return db.insert(SQLiteHelper.TABLE_PRODUCT_NAME, null, values);
    }
    //Update
    public int updateUser(Product datamodel) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.PRODUCT_ID, datamodel.getId());
        values.put(SQLiteHelper.PRODUCT_NAME, datamodel.getName());
        values.put(SQLiteHelper.PRODUCT_CATEGORY, datamodel.getCategory());
        values.put(SQLiteHelper.PRODUCT_QUANTITY, datamodel.getQuantity());
        values.put(SQLiteHelper.PRODUCT_CODE, datamodel.getCode());
        return db.update(SQLiteHelper.TABLE_PRODUCT_NAME, values, "id=?", new String[]{String.valueOf(datamodel.getId())});
    }
    //Delete by Id
    public int deleteCourse(int id) {
        return db.delete(SQLiteHelper.TABLE_PRODUCT_NAME, "Code=?", new String[]{String.valueOf(id)});
    }
    public int deleteproduct(Product contact) {
        return db.delete(SQLiteHelper.TABLE_PRODUCT_NAME, " Code=? ", new String[]{contact.getCode()});
    }
    ///////////////////////////////////////////////////
    // CREATE INSTANCE
    public static daoProducts getInstance(Context context) {
        if (instance == null) {
            instance = new daoProducts(context);
        }
        return instance;
    }

     /////////////////
    // TRỌNG HÙNG. //
    //
    // cái này dùng để thống kê hàng tồn kho. //
    public int Stats_total(){

        int total = 0;
        String sSQLite = "SELECT SUM(Quantity) FROM " + SQLiteHelper.TABLE_PRODUCT_NAME + " WHERE Quantity ";

        Cursor cursor = db.rawQuery( sSQLite,null );
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            total = cursor.getInt(0);
            cursor.moveToNext();
        }
        cursor.close();

        return total;

    }

    ///////////////////////////////////////////

    /////////////////
    // TRỌNG HÙNG. //
    //
    // cái này dùng để thống kê hàng tồn kho. ////
    // Bỏ. //
//    public Boolean Stats_byCategory(String name){
//        String sSQLite = "SELECT Category,Quantity "+
//                " FROM " + SQLiteHelper.TABLE_PRODUCT_NAME +
//                " WHERE " + SQLiteHelper.PRODUCT_CATEGORY + " = ? " +
//                " group by Category";
//
//        Cursor cursor = db.rawQuery( sSQLite,null );
//        cursor.moveToFirst();
//        while (cursor.isAfterLast()==false){
//           int i = cursor.getInt(0);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return Stats_byCategory(sSQLite);
//    }
//
//    //get By Id
//    public Product getBYCate(String nameCate) {
//        String sql = "SELECT * FROM " + SQLiteHelper.TABLE_PRODUCT_NAME + " WHERE Category=? ";
//        ArrayList<Product> list = getDataModels(sql, nameCate);
//        return list.get(0);
//    }



    ///////////////////////////////////////////

}
