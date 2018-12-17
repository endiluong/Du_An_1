package com.example.admin.du_an_1.UI;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admin.du_an_1.Controller.CategoryService;
import com.example.admin.du_an_1.Controller.TicketService;
import com.example.admin.du_an_1.DAO.daoProducts;
import com.example.admin.du_an_1.DAO.daoTicket;
import com.example.admin.du_an_1.R;
import com.example.admin.du_an_1.DAO.daoCategory;
import com.example.admin.du_an_1.Repository.Category;
import com.example.admin.du_an_1.Repository.Product;
import com.example.admin.du_an_1.Repository.Ticket;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {
    Category category;
    EditText etQuantityupdate, etProductnameupdate, etProductcodeupdate;
    Spinner spnCategoryupdate;
    Button btnAddTicketupdate, btnCancelupdate,  btnAddCategoryupdate, btnDateupdate;
    Bundle in;
    int mYear,mMonth,mDay;
    Product myProduct;
    Ticket myTicket;
    List<Product> arrproduct;
    private ArrayAdapter<Category> adapter;
    private daoCategory dao_Category;
    private daoTicket dao_Ticket;
    private daoProducts dao_Product;
    TicketService ticketService;
    private List<Category> lsCat;
    String strid;
    String stridticket;
    String strquan;
    String strcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        dao_Ticket = daoTicket.getInstance(this);
        dao_Product = daoProducts.getInstance(this);
        dao_Category = daoCategory.getInstance(this);
         lsCat = new ArrayList<>();
         lsCat = dao_Category.getAllItem();
        ticketService= new TicketService(this);
        // anh xa
        etQuantityupdate = (EditText) findViewById(R.id.etQuantityupdate);
        etProductnameupdate = (EditText) findViewById(R.id.etProductNameupdate);
        etProductcodeupdate = (EditText) findViewById(R.id.etProductCodeupdate);
        spnCategoryupdate = (Spinner) findViewById(R.id.spnCategoryupdate);
        btnAddCategoryupdate = (Button)findViewById(R.id.btnAddCategoryupdate);
        btnAddTicketupdate = (Button)findViewById(R.id.btnAddTicketupdate);
        btnCancelupdate = (Button)findViewById(R.id.btnCancelupdate);
        btnDateupdate = (Button)findViewById(R.id.etDateupdate);
        spnCategoryupdate = (Spinner)findViewById(R.id.spnCategoryupdate);

        initl();

        btnDateupdate.setOnClickListener(this);
        btnAddCategoryupdate.setOnClickListener(this);
        btnCancelupdate.setOnClickListener(this);
        btnAddTicketupdate.setOnClickListener(this);
    }

    private void initl() {

        ArrayList<String> arrName = new ArrayList<>();
        for (int i = 0; i < lsCat.size(); i++){
            arrName.add(lsCat.get(i).getName());
        }
        ArrayAdapter<String> adapterSpin = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,arrName);
        adapterSpin.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnCategoryupdate.setAdapter(adapterSpin);


        in = getIntent().getExtras();
        strid = in.getString("id");
        String strname = in.getString("name");
        strcode = in.getString("code");
        String strdate = in.getString("date");
        strquan = in.getString("quan");
        String strcat = in.getString("cat");
        stridticket = in.getString("idticket");

        mDay = Integer.parseInt(strdate.substring(0,2));
        mMonth = Integer.parseInt(strdate.substring(3,5))-1;
        mYear = Integer.parseInt(strdate.substring(6,10));
        etProductcodeupdate.setText(strcode);
        etProductnameupdate.setText(strname);
        btnDateupdate.setText(strdate);
        etQuantityupdate.setText(strquan);
        for (String temp : arrName){
            if (temp.equals(strcat)){
                spnCategoryupdate.setSelection(arrName.indexOf(strcat));
                break;
            }
            else spnCategoryupdate.setSelected(false);
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.etDateupdate:
                DatePicker();
                break;
            case R.id.btnAddCategoryupdate:
                LayoutInflater inflater = getLayoutInflater();
                v = inflater.inflate( R.layout.dialog_add_category,null );

                final EditText edtDialog_addCategory = (EditText)v.findViewById( R.id.edtDialog_addCategory );

                AlertDialog.Builder mBuilder = new AlertDialog.Builder( this );
                mBuilder.setView( v ).setTitle("ADD CATEGORY").setPositiveButton( "Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CategoryService ServiceCat = new CategoryService( getBaseContext() );
                        ServiceCat.addCategory( edtDialog_addCategory.getText().toString() );
                        finish();
                        startActivityForResult( new Intent( getBaseContext(),UpdateActivity.class ),0 );
                    }
                } ).setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        dialogInterface.cancel();
                    }
                } ).create().show();
                break;
            case R.id.btnCancelupdate:
                this.finish();
                break;
            case R.id.btnAddTicketupdate:
                if (validate()){
                    if (ticketService.addImport(addTicket(),addproduct(),"update")){
                        Toast.makeText(this, "Update thanh cong", Toast.LENGTH_SHORT).show();
                        this.finish();
                    }
                }
                //                dao_Product.updateUser(addproduct());
//                dao_Ticket.insertTicket(addTicket());

                break;
        }
    }



    //validate
    public boolean validate(){

        if (etProductcodeupdate.getText().toString().isEmpty()){
            Toast.makeText(this, "Product code null", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etProductnameupdate.getText().toString().equals("")){
            Toast.makeText(this, "Peoduct name null", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etQuantityupdate.getText().toString().equals("")){
            Toast.makeText(this, "Quantily null", Toast.LENGTH_SHORT).show();
            return false;
        }else if (etQuantityupdate.getText().toString().equals("0")){
            Toast.makeText(this, "Khong dc nhap Quanlity = 0", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (spnCategoryupdate.getCount()==0){
            Toast.makeText(this, "Category null", Toast.LENGTH_SHORT).show();
            return false;
        }else {arrproduct = new ArrayList<Product>();
            arrproduct = dao_Product.getAllItem();
            List<String> arrcode = new ArrayList<>();
            for (Product temp : arrproduct){
                arrcode.add(temp.getCode());
            }
            arrcode.remove(strcode);
            for (String tempcode : arrcode){
                if (etProductcodeupdate.getText().toString().equals(tempcode)){
                    Toast.makeText(this, "Product code exit", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }}
        return true;
    }

    private void DatePicker() {

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        if (dayOfMonth<10&&monthOfYear<9){
                            btnDateupdate.setText("0"+dayOfMonth + "-" + "0"+(monthOfYear + 1) + "-" + year);
                        }
                        else if(dayOfMonth<10 ){
                            btnDateupdate.setText("0"+dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }else if (monthOfYear<9){
                            btnDateupdate.setText(dayOfMonth + "-" + "0"+(monthOfYear + 1) + "-" + year);
                        }else btnDateupdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
    public Product addproduct(){
        myProduct = new Product();
        myProduct.setCode(etProductcodeupdate.getText().toString());
        myProduct.setName(etProductnameupdate.getText().toString());
        myProduct.setCategory(spnCategoryupdate.getSelectedItem().toString());
        myProduct.setId(strid);
        return myProduct;
    }

    public Ticket addTicket(){
            myTicket = new Ticket();
            myTicket.setDate(btnDateupdate.getText().toString());
            myTicket.setType(true);
            myTicket.setId(stridticket);
            myTicket.setproductCode(etProductcodeupdate.getText().toString());
            myTicket.setQuantity(Integer.parseInt(etQuantityupdate.getText().toString()));
            return myTicket;
    }
}
