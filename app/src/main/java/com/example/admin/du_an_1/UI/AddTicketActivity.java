package com.example.admin.du_an_1.UI;

import android.app.DatePickerDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.admin.du_an_1.Adapter.TabAdapter;
import com.example.admin.du_an_1.Controller.TicketService;
import com.example.admin.du_an_1.R;
import com.example.admin.du_an_1.Repository.Product;
import com.example.admin.du_an_1.Repository.Ticket;

import java.util.Calendar;


public class AddTicketActivity extends AppCompatActivity implements View.OnClickListener {
    TicketService ticketService;
    EditText etTicketTittle, etQuantity, etProductname, etProductcode;
    Spinner spProductId,spncat;
    Button btnAddTicket, btnCancel,  btnAddCategory,etDate;

    Product myProduct;
    Ticket myTicket;
    int mYear,mMonth,mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ticket);

        ticketService= new TicketService(this);
        //CREATE FAKE DATA FOR TESTING
        etQuantity = (EditText) findViewById(R.id.etQuantity);
        etDate = (Button) findViewById (R.id.etDate);
        etProductname = (EditText)findViewById(R.id.etProductName);
        etProductcode = (EditText)findViewById(R.id.etProductCode);
        spncat = (Spinner)findViewById(R.id.spnCategory);
        btnAddTicket= (Button)findViewById(R.id.btnAddTicket);
        btnAddTicket.setOnClickListener(this);
        etDate.setOnClickListener(this);
        final Calendar c = Calendar.getInstance();
         mYear = c.get(Calendar.YEAR);
         mMonth = c.get(Calendar.MONTH);
         mDay = c.get(Calendar.DAY_OF_MONTH);

        etDate.setText((mDay + "-" + mMonth + "-" + mYear));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.btnAddTicket):
//                asdasdasdasd
//                        asdasdasdasdasdasd
//                        asdasdasdasdasdasd
//                                Product temp = new Product(id,name,iasd,asdasd,);
                if (onAddClicked(addTicket(), addproduct(), "Import")){
                    this.finish();
                }
                break;
            case (R.id.btnCancel):
                break;
            case (R.id.etDate):
                DatePicker();
                break;
        }

    }
    public Product addproduct(){
        myProduct = new Product();
        myProduct.setCode(etProductcode.getText().toString());
        myProduct.setName(etProductname.getText().toString());
        myProduct.setCategory(null);
        myProduct.setId(null);
        return myProduct;
    }

    public Ticket addTicket(){
        myTicket = new Ticket();
        myTicket.setDate(etDate.getText().toString());
        myTicket.setType(null);
        myTicket.setId(null);
        myTicket.setproductName(etProductname.getText().toString());
        myTicket.setQuantity(Integer.parseInt(etQuantity.getText().toString()));
        return myTicket;
    }

    public boolean onAddClicked(Ticket ticket, Product product, String tittle) {
        if (this.ticketService.addImport(ticket, product)){
            return true;
        }
        return false;
    }
    private void DatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        etDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
