package com.example.admin.du_an_1.UI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.admin.du_an_1.Adapter.TabAdapter;
import com.example.admin.du_an_1.Controller.CategoryService;
import com.example.admin.du_an_1.Controller.TicketService;
import com.example.admin.du_an_1.DAO.daoCategory;
import com.example.admin.du_an_1.R;
import com.example.admin.du_an_1.Repository.Category;
import com.example.admin.du_an_1.Repository.Product;
import com.example.admin.du_an_1.Repository.Ticket;

import java.util.ArrayList;


public class AddTicketActivity extends AppCompatActivity implements View.OnClickListener {
    TicketService ticketService;
    EditText etTicketTittle, etQuantity, etDate, etProductName;

//>>EDIT:    Spinner spProductId;
    Spinner spnCategory;
    Button btnAddTicket, btnCancel,  btnAddCategory;

    Product myProduct;
    Ticket myTicket;

    //TEST. ///

    private daoCategory DAOcat;
    private ArrayList<Category> arrCat;
    private Category category;

    //////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ticket);

        ticketService= new TicketService(this);
        //CREATE FAKE DATA FOR TESTING
        myProduct= new Product();
        myTicket= new Ticket();


        btnAddCategory = findViewById( R.id.btnAddCategory );
        spnCategory = (Spinner) findViewById( R.id.spnCategory );

        btnAddTicket= (Button)findViewById(R.id.btnAddTicket);

        btnAddTicket.setOnClickListener(this);
        btnAddCategory.setOnClickListener( this );

    }

    @Override
    protected void onResume() {
        super.onResume();
        initial();


    }

    private void initial() {
        DAOcat = daoCategory.getInstance(getBaseContext());
        arrCat = DAOcat.getAllItem();
        ArrayList<String> arrName = new ArrayList<>();
        for (int i = 0; i < arrCat.size(); i++){
            arrName.add(arrCat.get(i).getName());
        }
        ArrayAdapter<String> adapterSpin = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,arrName);
        adapterSpin.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnCategory.setAdapter(adapterSpin);
//        spnCategory.setOnItemSelectedListener(this);

        // Refresh Data. //
        adapterSpin.notifyDataSetChanged();

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.btnAddTicket):
                onAddClicked(myTicket, myProduct, "Import");
                break;

            case R.id.btnAddCategory:
                LayoutInflater inflater = getLayoutInflater();
                view = inflater.inflate( R.layout.dialog_add_category,null );

                final EditText edtDialog_addCategory = (EditText)view.findViewById( R.id.edtDialog_addCategory );

                AlertDialog.Builder mBuilder = new AlertDialog.Builder( this );
                mBuilder.setView( view ).setTitle("ADD CATEGORY").setPositiveButton( "Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CategoryService ServiceCat = new CategoryService( getBaseContext() );
                        ServiceCat.addCategory( edtDialog_addCategory.getText().toString() );
                        finish();
                        startActivityForResult( new Intent( getBaseContext(),AddTicketActivity.class ),0 );
                    }
                } ).setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        dialogInterface.cancel();
                    }
                } ).create().show();
                break;
        }

    }

    public void onAddClicked(Ticket ticket, Product product, String tittle) {
        this.ticketService.addTicket(ticket, product, tittle);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void Dialog_Add(){



    }
}
