package com.example.admin.du_an_1.UI;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.admin.du_an_1.Adapter.TabAdapter;
import com.example.admin.du_an_1.Controller.TicketService;
import com.example.admin.du_an_1.R;
import com.example.admin.du_an_1.Repository.Product;
import com.example.admin.du_an_1.Repository.Ticket;


public class AddTicketActivity extends AppCompatActivity implements View.OnClickListener {
    TicketService ticketService;
    EditText etTicketTittle, etQuantity, etDate;
    Spinner spProductId;
    Button btnAddTicket, btnCancel,  btnAddCategory;

    Product myProduct;
    Ticket myTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ticket);

        ticketService= new TicketService(this);
        //CREATE FAKE DATA FOR TESTING
        myProduct= new Product();
        myTicket= new Ticket();

        btnAddTicket= (Button)findViewById(R.id.btnAddTicket);
        btnAddTicket.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.btnAddTicket):
                onAddClicked(myTicket, myProduct, "Import");
                break;
        }

    }

    public void onAddClicked(Ticket ticket, Product product, String tittle) {
        this.ticketService.addTicket(ticket, product, tittle);
    }
}
