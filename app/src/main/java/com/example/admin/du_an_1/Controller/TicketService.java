package com.example.admin.du_an_1.Controller;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.admin.du_an_1.DAO.daoCategory;
import com.example.admin.du_an_1.Repository.Category;
import com.example.admin.du_an_1.Repository.Product;
import com.example.admin.du_an_1.Repository.Ticket;

import java.util.ArrayList;
import android.widget.Toast;

import com.example.admin.du_an_1.DAO.daoProducts;
import com.example.admin.du_an_1.DAO.daoTicket;
import com.example.admin.du_an_1.Repository.Product;
import com.example.admin.du_an_1.Repository.Ticket;


import java.util.List;

public class TicketService {
    Context context;
    daoCategory daoCat;
    daoProducts DaoProducts;
    daoTicket DaoTicket;
    List<Product> lis;

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

    public boolean addImport(Ticket ticket, Product product,String os) {

        // if validate true >>add sucess
      //  validateTicket(product,ticket);
    lis = new ArrayList<>();
        lis = DaoProducts.getAllItem();
        if (os.equals("nhap")) {
            if (validateTicket(product,ticket)){
                for (Product temp : lis){
                    String tempcode = temp.getCode();
                    String tempname = temp.getName();
                    if (tempcode.equals(product.getCode()) && tempname.equals(product.getName())){
                        Ticket ticketold = DaoTicket.getByCode(product.getCode());
                        Ticket tempp = new Ticket();
                        tempp.setId(null);
                        tempp.setproductCode(ticketold.getproductCode());
                        tempp.setDate(ticket.getDate());
                        tempp.setType(true);
                        tempp.setQuantity(ticketold.getQuantity()+(ticket.getQuantity()));
                        DaoTicket.insertTicket(tempp);
                        Toast.makeText(context, "Add Success", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }
                DaoTicket.insertTicket(ticket);
                DaoProducts.insertProduct(product);
                Toast.makeText(context, "Add Success", Toast.LENGTH_SHORT).show();
                return true;
            }

        }
        else if (os.equals("update")){
                DaoTicket.insertTicket(ticket);
                DaoProducts.updateUser(product);
                return true;

        }else{
            //handle error
            Toast.makeText(context, "Add Fail", Toast.LENGTH_SHORT).show();
            return false;
        }
        return false;
    }


    public boolean addTicket(Ticket ticket) {
        DaoTicket.insertTicket(ticket);
        // Add Ticket to TABLE with type export
        return true;
    }

    private boolean validateTicket(Product product, Ticket ticket){
        List<Product> listproduct = DaoProducts.getAllItem();
        List<Ticket> listticket = DaoTicket.getAllItem();
        String strcodeproduct = product.getCode();
        int intquantity = ticket.getQuantity();
     //   String codeTicket = ticket.getproductCode();
        for (int i = 0; i<listproduct.size();i++){
            Product temp = listproduct.get(i);
            if (strcodeproduct.equals(temp.getCode()) && product.getName().equals(temp.getName()) && product.getCategory().equals(temp.getCategory())){
                return true;
            }
            else if (strcodeproduct.equals(temp.getCode()) && product.getName().equals(temp.getName())){
                Toast.makeText(context, "Category must be " + temp.getCategory() , Toast.LENGTH_SHORT).show();
                return false;
            }
            else if (strcodeproduct.equals(temp.getCode())){
                Toast.makeText(context, "Product code exits and Product name is " + temp.getName() , Toast.LENGTH_SHORT).show();
                return false;
             }
                else if (intquantity < 0){
                Toast.makeText(context, "Quantily epual 0", Toast.LENGTH_SHORT).show();
                return false;
            }

        }
        return true;
    }


//    private boolean validateTicketupdate(Product product, Ticket ticket){
//        List<Product> listproduct = DaoProducts.getAllItem();
//        List<Ticket> listticket = DaoTicket.getAllItem();
//        String strcodeproduct = product.getCode();
//        int intquantity = ticket.getQuantity();
//        String codeTicket = ticket.getproductName();
//        List <String> arrcode = null;
//        for (int i = 0; i<listproduct.size();i++){
//             arrcode = new ArrayList();
//            Product temp = listproduct.get(i);
//            String strcode=temp.getCode();
//            arrcode.add(strcode);
//        }
//        arrcode.remove(strcodeproduct);
//            for (String temp : arrcode){
//                if(strcodeproduct.equals(temp)){
//                    Toast.makeText(context, "Product code exits ", Toast.LENGTH_SHORT).show();
//                    return false;
//                }
//                else if (intquantity < 0){
//                    Toast.makeText(context, "Quantily epual 0", Toast.LENGTH_SHORT).show();
//                    return false;
//                }
//            }
//
//        return true;
//    }

    private void addExport(Ticket ticket, Product product) {
        Toast.makeText(context, "Add Export", Toast.LENGTH_SHORT).show();
        // Add Ticket to TABLE with type export
    }


}
