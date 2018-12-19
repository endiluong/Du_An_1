package com.example.admin.du_an_1.UI.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.du_an_1.Adapter.StatsCategoryAdapter;
import com.example.admin.du_an_1.Controller.StatsService;
import com.example.admin.du_an_1.DAO.daoCategory;
import com.example.admin.du_an_1.DAO.daoProducts;
import com.example.admin.du_an_1.DAO.daoTicket;
import com.example.admin.du_an_1.R;
import com.example.admin.du_an_1.Repository.Category;
import com.example.admin.du_an_1.Repository.Product;
import com.example.admin.du_an_1.Repository.Ticket;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Stats extends Fragment {

     //////////////////
    //              //
    TextView tvTotal;
    Spinner spnCate_find;
    ListView lvFind_byCate;
    StatsCategoryAdapter adapter;

    //Product. //
    Product product;
    daoProducts myDAO_Products;
    ArrayList<Product> arrProducts;
//    ArrayList<Product> arrProductnamecate;
//    ArrayList<String> arrnameCate;

    //Category. //
    Category category;
    daoCategory myDAO_Cate;
    ArrayList<Category> arrCate;

    Context context;

//    String name = null;
//    ArrayList<Ticket> arrTicket;

    public Fragment_Stats() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_stats, container, false );

        tvTotal = (TextView) view.findViewById( R.id.tvTotal );
        Button btnFind = (Button)view.findViewById( R.id.btnFind );

        spnCate_find = (Spinner)view.findViewById( R.id.spnCate_find );

        lvFind_byCate = (ListView)view.findViewById( R.id.lvFind_byCate );

        context= getActivity();
        btnFind.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        checkByCategory(spnCate_find.getSelectedItem().toString());
            }
        } );
        return view;
    }

    @Override
    public void onResume() {
        //Products.
        myDAO_Products = daoProducts.getInstance( context );
        arrProducts = myDAO_Products.getAllItem();
        initial();
        checkByCategory(spnCate_find.getSelectedItem().toString());
        tvTotal.setText( String.valueOf( myDAO_Products.Stats_total() ) );

        super.onResume();
    }

    private void initial() {
        myDAO_Cate = daoCategory.getInstance(context);
        arrCate = myDAO_Cate.getAllItem();
        ArrayList<String> arrName = new ArrayList<>();
        for (int i = 0; i < arrCate.size(); i++){
            arrName.add(arrCate.get(i).getName());
        }
        ArrayAdapter<String> adapterSpin = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,arrName);
        adapterSpin.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnCate_find.setAdapter(adapterSpin);
        // Refresh Data. //
        adapter.notifyDataSetChanged();
    }

    public Boolean checkByCategory(String nameCate){
//        initial();
        myDAO_Products = daoProducts.getInstance( context );
        arrProducts = myDAO_Products.getAllItem();
        myDAO_Cate = daoCategory.getInstance( context );
        arrCate = myDAO_Cate.getAllItem();

        for (int i = 0;i<arrProducts.size();i++){
            product = arrProducts.get( i );
//            product = myDAO_Products.getBYCate( nameCate );
            if (nameCate.equals( product.getCategory() )){
                StatsCategoryAdapter adapter = new StatsCategoryAdapter( context,R.layout.item_stats_category,arrProducts );
                lvFind_byCate.setAdapter( adapter );

                Toast.makeText( context,"Have",Toast.LENGTH_SHORT ).show();
                return true;
            }else {
                Toast.makeText( context,"Haven't",Toast.LENGTH_SHORT ).show();
                return false;
            }

        }

        return false;
    }
}
