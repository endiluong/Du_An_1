package com.example.admin.du_an_1.UI;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.du_an_1.Adapter.CategoryAdap;
import com.example.admin.du_an_1.Controller.CategoryService;
import com.example.admin.du_an_1.DAO.daoCategory;
import com.example.admin.du_an_1.R;
import com.example.admin.du_an_1.Repository.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    FloatingActionButton btnAdd_Category;
    ListView lv_Category;

    Category category;

    private ArrayAdapter<Category> adapter;
    private daoCategory dao_Category;
    CategoryService service;

    private List<Category> lsCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        lv_Category = findViewById( R.id.lv_Category );
        btnAdd_Category = findViewById( R.id.btnAdd_Category );
        btnAdd_Category.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog_Add(view);
            }
        } );
    }

    @Override
    protected void onResume() {
        super.onResume();


        dao_Category = daoCategory.getInstance(this);
        lsCat = dao_Category.getAllItem();

        adapter = new CategoryAdap( this,R.layout.item_cat,(ArrayList<Category>)lsCat );
        lv_Category.setAdapter( adapter );
//        mList_type.clear();
        adapter.notifyDataSetChanged();

        lv_Category.setOnItemClickListener( this );

    }

    //
    //
    //////////////////////////////////////
    // Sử dụng Dialog để thêm Thể Loại. //
    //
    //
    public void Dialog_Add(View view){

        LayoutInflater inflater = getLayoutInflater();
        view = inflater.inflate( R.layout.dialog_add_category,null );

        final EditText edtDialog_addCategory = (EditText)view.findViewById( R.id.edtDialog_addCategory );

        AlertDialog.Builder mBuilder = new AlertDialog.Builder( this );
        mBuilder.setView( view ).setTitle("ADD CATEGORY").setPositiveButton( "Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                CategoryService ServiceCat = new CategoryService( getBaseContext() );
                ServiceCat.addCategory( edtDialog_addCategory.getText().toString() );
                finish();
                startActivityForResult( new Intent( getBaseContext(),CategoryActivity.class ),0 );
            }
        } ).setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                dialogInterface.cancel();
            }
        } ).create().show();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
        AlertDialog.Builder builder = new AlertDialog.Builder( CategoryActivity.this );
        builder.setTitle( "Delete Category")
                .setMessage( "This is "+lsCat.get( i ).getName()+ "\n"+ "Are You Sure ?" ).setCancelable( false )
                .setPositiveButton( "Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        category = lsCat.get( i );
                            int success;
                            if (category != null){
                                success = dao_Category.deleteCat( category );
                                if (success > 0) {
                                    Toast.makeText( getBaseContext(), "Was Delete", Toast.LENGTH_SHORT ).show();
                                }
                            } else {
                                Toast.makeText( getBaseContext(), "Can't Delete, Cause not data", Toast.LENGTH_SHORT ).show();
                            }

                        startActivityForResult( new Intent( getBaseContext(),CategoryActivity.class ),0 );
//
                        finish();
                    }
                } )
                .setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                } ).create().show();
    }

    public void dialogDel(final int position){

        //        CategoryService serCat = new CategoryService( getBaseContext() );
        //        serCat.delCat( i );
    }
}
