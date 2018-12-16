package com.example.admin.du_an_1.UI.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.admin.du_an_1.Adapter.ListStatsAdapter;
import com.example.admin.du_an_1.DAO.daoTicket;
import com.example.admin.du_an_1.R;
import com.example.admin.du_an_1.Repository.Ticket;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_ListStats extends Fragment {

    ArrayList<Ticket> arrTicket;
    daoTicket mDAO_ticket;
    Ticket ticket;

    public Fragment_ListStats() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_list_stats, container, false );

//        FrameLayout frLayout = view.findViewById( R.id.frLayout );
//        Fragment_ListStats listStats = new Fragment_ListStats();
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.replace( R.id.frLayout,listStats ).addToBackStack( null ).commit();

        mDAO_ticket = daoTicket.getInstance( getContext() );
        arrTicket = mDAO_ticket.getAllItem();

        ListStatsAdapter adapter = new ListStatsAdapter( getContext(),R.layout.item_out, arrTicket);
        ListView lvList_stats = view.findViewById( R.id.lvList_stats );
        lvList_stats.setAdapter( adapter );

        return view;
    }

}
