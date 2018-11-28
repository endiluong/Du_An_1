//package com.example.admin.du_an_1.UI.Fragment;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.TextView;
//
//import com.example.admin.du_an_1.Controller.TicketService;
//import com.example.admin.du_an_1.R;
//import com.example.admin.du_an_1.Repository.Product;
//import com.example.admin.du_an_1.Repository.Ticket;
//
//public class Fragment_AddTicket extends Fragment implements View.OnClickListener {
//    TicketService ticketService;
//    EditText etTicketTittle, etQuantity, etDate;
//    Spinner spProductId;
//    Button btnAddTicket, btnCancel,  btnAddCategory;
//
//    Product myProduct;
//    Ticket myTicket;
//
//    public Fragment_AddTicket() {
//    }
//
//    public static Fragment_AddTicket newInstance(String title) {
//        Fragment_AddTicket fragment = new Fragment_AddTicket();
//        Bundle args = new Bundle();
//        args.putCharSequence("title", title);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // Create instance context for Service and furthur use, user context instead getActivity()
//        context=getActivity();
//        // Set Tittle for Ticket
//        ticketService= new TicketService(context);
//        //CREATE FAKE DATA FOR TESTING
//        myProduct= new Product();
//        myTicket= new Ticket();
//
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view= inflater.inflate(R.layout.add_ticket_fragment, container, false);
//        // DECLEARATION
//        tvTittle=(TextView) view.findViewById(R.id.tvTicket);
//        btnAddTicket= (Button) view.findViewById(R.id.btnAddTicket);
//
//
//        //SETUP FUNC for ELEMENT
//         tvTittle.setText(getTitle());
//        tittle =getTitle();
//        btnAddTicket.setOnClickListener(this);
//
//
//        return view;
//    }
//
//    public void onAddClicked(Ticket ticket, Product product,String tittle){
//        this.ticketService.addTicket(ticket,product,tittle);
//    }
//
//    public void onCancelClicked(){}
//
//    public String getTitle() {
//        Bundle args = getArguments();
//        return this.ticketService.getTitle(args);
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case (R.id.btnAddTicket):
//                onAddClicked(myTicket,myProduct,tittle);
//                break;
//        }
//
//    }
//}
