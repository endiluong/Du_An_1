package com.example.admin.du_an_1.UI.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.du_an_1.Adapter.ProductAdapter;
import com.example.admin.du_an_1.DAO.daoProducts;
import com.example.admin.du_an_1.DAO.daoTicket;
import com.example.admin.du_an_1.R;
import com.example.admin.du_an_1.Repository.Product;
import com.example.admin.du_an_1.Repository.Ticket;
import com.example.admin.du_an_1.UI.AddTicketActivity;
import com.example.admin.du_an_1.UI.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class Fragment_List extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    FloatingActionButton fab;
    ListView productList;
    Context context;
    daoProducts DaoProducts;
    daoTicket DaoTicket;
    List<Product> listProduct;
    List<Ticket> listTeck;


    ProductAdapter productAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        DaoProducts = daoProducts.getInstance(context);
        DaoTicket = daoTicket.getInstance(context);



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_list, container, false);
        fab= (FloatingActionButton) view.findViewById(R.id.btnFAB_Add);
        productList= (ListView) view.findViewById(R.id.lv_product);
        fab.setOnClickListener(this);
        // add products up list
        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               List<Product>  listproduct1 = DaoProducts.getAllItem();
               //List<Ticket>  listticket1 = DaoTicket.getAllItem();
               final Product productdilog = listproduct1.get(i);
               Ticket ticket1 = DaoTicket.getByName(productdilog.getName());

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.item_option_product);
                dialog.setCancelable(true);

                TextView tvname = (TextView)dialog.findViewById(R.id.tvname);
                TextView tvcode = (TextView)dialog.findViewById(R.id.tvcode);
                TextView tvsoluong = (TextView)dialog.findViewById(R.id.tvsoluong);
                Button btnsua = (Button) dialog.findViewById(R.id.btnhuy);
                Button btnxoa = (Button)dialog.findViewById(R.id.btnxoa);
                Button btnxuat = (Button) dialog.findViewById(R.id.btnxuat);

                tvname.setText(productdilog.getName());
                tvcode.setText(productdilog.getCode());
                tvsoluong.setText(String.valueOf(ticket1.getQuantity()));

                //

                // btn xoa
                btnxoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DaoProducts.deleteproduct(productdilog);
                        listProduct = DaoProducts.getAllItem();
                        productAdapter = new ProductAdapter(context,listProduct);
                        productList.setAdapter(productAdapter);
                        productList.deferNotifyDataSetChanged();
                        dialog.cancel();
                    }
                });
// button huy
                Button btnhuy = (Button) dialog.findViewById(R.id.btnhuy);
                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        listTeck = DaoTicket.getAllItem();

        listProduct = DaoProducts.getAllItem();
        productAdapter = new ProductAdapter(context,listProduct);
        productList.setAdapter(productAdapter);
        productList.deferNotifyDataSetChanged();

        super.onResume();
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getActivity(), AddTicketActivity.class));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

//    public void DialogInfo(Inflater inflater){
//        View view =
//    }
}
