package com.example.admin.du_an_1.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.admin.du_an_1.R;
import com.example.admin.du_an_1.Repository.Category;

import java.util.ArrayList;

public class CategoryAdap extends ArrayAdapter<Category> {

    Activity con;
    int layout;
    ArrayList<Category> arrCat;

    public CategoryAdap(@NonNull Activity context, int resource, @NonNull  ArrayList<Category> objects) {
        super( context, resource, objects );
        this.con = context;
        this.layout = resource;
        this.arrCat = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = con.getLayoutInflater();
        convertView = inflater.inflate( layout,null );

        TextView tvID_Cat = (TextView)convertView.findViewById( R.id.tvID_Cat );
        TextView tvName_Cat = (TextView)convertView.findViewById( R.id.tvName_Cat );

        Category temp = arrCat.get( position );
        tvID_Cat.setText( temp.getId() );
        tvName_Cat.setText( temp.getName() );


        return convertView;
    }
}
