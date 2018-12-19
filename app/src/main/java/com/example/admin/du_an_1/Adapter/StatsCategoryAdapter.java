package com.example.admin.du_an_1.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.admin.du_an_1.R;
import com.example.admin.du_an_1.Repository.Product;

import java.util.ArrayList;

public class StatsCategoryAdapter extends ArrayAdapter<Product> {

    Context context;
    int layout;
    ArrayList<Product> arrProducts;

    public StatsCategoryAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Product> objects) {
        super( context, resource, objects );
        this.context = context;
        this.layout = resource;
        this.arrProducts = objects;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from( context ).inflate( layout,null );


        // NOTES: đay là tên Thể Loại. //
        TextView tvName_statsCate = (TextView) convertView.findViewById( R.id.tvName_statsCate );
        TextView tvQuantity_statsCate = (TextView) convertView.findViewById( R.id.tvQuantity_statsCate );

        Product temp = arrProducts.get( position );
        tvName_statsCate.setText( temp.getCategory() );
        tvQuantity_statsCate.setText( String.valueOf( temp.getQuantity() ) );

        return convertView;
    }
}
