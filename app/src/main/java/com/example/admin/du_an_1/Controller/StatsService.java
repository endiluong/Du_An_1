package com.example.admin.du_an_1.Controller;

import android.content.Context;

import com.example.admin.du_an_1.DAO.daoTicket;
import com.example.admin.du_an_1.Repository.Ticket;

import java.util.ArrayList;

public class StatsService {

    Context context;
    Ticket ticket;
    daoTicket myDaoTicket;
    ArrayList<Ticket> arrTicket;


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
}
