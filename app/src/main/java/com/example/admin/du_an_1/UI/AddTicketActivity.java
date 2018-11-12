package com.example.admin.du_an_1.UI;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.du_an_1.Adapter.TabAdapter;
import com.example.admin.du_an_1.R;
import com.example.admin.du_an_1.UI.Fragment.Fragment_AddTicket;

public class AddTicketActivity extends AppCompatActivity {
    TabAdapter tabAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ticket);


        tabLayout=findViewById(R.id.tabLayoutTicket);
        viewPager=findViewById(R.id.viewPagerTicket);

        tabAdapter= new TabAdapter(getSupportFragmentManager());
        tabAdapter.addFragment(Fragment_AddTicket.newInstance("Import"),"Import");
        tabAdapter.addFragment(Fragment_AddTicket.newInstance("Export"),"Export");


        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
