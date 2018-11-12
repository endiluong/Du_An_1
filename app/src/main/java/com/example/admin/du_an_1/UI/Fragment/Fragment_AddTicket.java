package com.example.admin.du_an_1.UI.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.admin.du_an_1.Controller.TicketService;
import com.example.admin.du_an_1.R;
import com.example.admin.du_an_1.Repository.Ticket;

public class Fragment_AddTicket extends Fragment{
    TicketService ticketService;
    Context context;
    EditText etTicketTittle, etQuantity, etDate;
    Spinner spProductId;
    Button btnAddTicket, btnCancel, btnAddProduct, btnAddCategory;

    public Fragment_AddTicket() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create instance context for Service and furthur use, user context instead getActivity()
        context=getActivity();
        ticketService= new TicketService(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.add_ticket_fragment, container, false);
        
        return view;
    }

    public void onAddClicked(){}
    public void onCancelClicked(){}
}
