package com.example.admin.du_an_1.Controller;

import android.content.Context;
import android.widget.Toast;

import com.example.admin.du_an_1.DAO.daoProducts;
import com.example.admin.du_an_1.DAO.daoTicket;
import com.example.admin.du_an_1.Repository.Product;
import com.example.admin.du_an_1.Repository.Ticket;

import java.util.ArrayList;

public class StatsService {

    Context context;
    Ticket ticket;
    daoTicket myDaoTicket;
    ArrayList<Ticket> arrTicket;

    Product product;
    daoProducts myDAO_Products;
    ArrayList<Product> arrProducts;




    public StatsService(Context context) {
        this.context = context;
    }

    public Boolean insertExport(String name, int quantity_out){
        myDaoTicket = daoTicket.getInstance( context );
        arrTicket = myDaoTicket.getAllItem();
        for (int i = 0;i<arrTicket.size();i++){
            ticket = arrTicket.get( i );
            if (name.equals( ticket.getproductCode() )){
                name = ticket.getproductCode();
                ticket.setQuantity( quantity_out );
                myDaoTicket.insertExport( ticket );
                return true;
            }else {
                ticket.setproductCode( name );
                ticket.setQuantity( quantity_out );
                myDaoTicket.insertExport( ticket );
                return false;
            }
        }


        return false;
    }


    public Boolean checkByCategory(String nameCate){
        myDAO_Products = daoProducts.getInstance( context );
        arrProducts = myDAO_Products.getAllItem();
        for (int i = 0;i<arrProducts.size();i++){
            product = arrProducts.get( i );
//            product = myDAO_Products.getBYCate( nameCate );
            if (product.getCategory().equals( nameCate )){
                Toast.makeText( context,"Have",Toast.LENGTH_SHORT ).show();
                return true;
            }else {
                Toast.makeText( context,"Haven't",Toast.LENGTH_SHORT ).show();
                return false;
            }
//            if (arrProducts.get( i ).getCategory().equals( myDAO_Products.Stats_byCategory( nameCate ) ))
        }

        return false;
    }
}
