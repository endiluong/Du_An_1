package com.example.admin.du_an_1.Controller;

import android.content.Context;
import android.widget.Toast;

import com.example.admin.du_an_1.DAO.daoProducts;
import com.example.admin.du_an_1.DAO.daoTicket;
import com.example.admin.du_an_1.Repository.Product;
import com.example.admin.du_an_1.Repository.Ticket;

import java.util.List;

public class TicketService {
    Context context;
    daoProducts DaoProducts;
    daoTicket DaoTicket;

    public TicketService(Context context) {
        this.context = context;
        DaoProducts = daoProducts.getInstance(context);
        DaoTicket = daoTicket.getInstance(context);
    }

    public Boolean setType(String typeString) {
        if (typeString.equals("Export")) {
            return true;
        } else
            return false;
    }

//    public boolean addTicket(Ticket ticket, Product product, String ticketType) {
//        if (setType(ticketType)) {
//            addExport(ticket, product);
//            return true;
//        } else {
//            addImport(ticket, product);
//
//        }
//    }

    public boolean addImport(Ticket ticket, Product product) {

        // if validate true >>add sucess
      //  validateTicket(product,ticket);
        if (validateTicket(product,ticket)) {
            DaoTicket.insertTicket(ticket);
            DaoProducts.insertProduct(product);
            Toast.makeText(context, "Add Success", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            //handle error
            Toast.makeText(context, "Add Fail", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean validateTicket(Product product, Ticket ticket){
        List<Product> listproduct = DaoProducts.getAllItem();
        List<Ticket> listticket = DaoTicket.getAllItem();
        String strcodeproduct = product.getCode();
        int intquantity = ticket.getQuantity();
        // em chuyen cai productname trong ticket sang productcode (Ten co the trung`)
        String codeTicket = ticket.getproductName();
        for (int i = 0; i<listproduct.size();i++){
            Product temp = listproduct.get(i);
            if (strcodeproduct.equals(temp.getCode())){
                Toast.makeText(context, "Product code exits", Toast.LENGTH_SHORT).show();
                return false;
            }
            else if (intquantity < 0){
                Toast.makeText(context, "Quantily epual 0", Toast.LENGTH_SHORT).show();
                return false;
            }

        }
        return true;
    }
    private void addExport(Ticket ticket, Product product) {
        Toast.makeText(context, "Add Export", Toast.LENGTH_SHORT).show();
        // Add Ticket to TABLE with type export
    }
}
