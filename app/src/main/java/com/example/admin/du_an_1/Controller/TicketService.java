package com.example.admin.du_an_1.Controller;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.admin.du_an_1.Repository.Product;
import com.example.admin.du_an_1.Repository.Ticket;

public class TicketService {
    Context context;

    public TicketService(Context context) {
        this.context = context;
    }

    public Boolean setType(String typeString) {
        if (typeString.equals("Export")) {
            return true ;
        } else
            return false;
    }

    public void addTicket(Ticket ticket, Product product,String ticketType){
        if(setType(ticketType)){
            addExport(ticket,product);
        }
        else{
            addImport(ticket,product);
        }
    }

    private void addImport(Ticket ticket, Product product){
        Toast.makeText(context, "Add Import", Toast.LENGTH_SHORT).show();
        // Add Ticket to TABLE with type import
    }

    private void addExport(Ticket ticket, Product product){
        Toast.makeText(context, "Add Export", Toast.LENGTH_SHORT).show();
        // Add Ticket to TABLE with type export
    }
}
