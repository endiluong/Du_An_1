package com.example.admin.du_an_1.UI;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.admin.du_an_1.Adapter.TabAdapter;
import com.example.admin.du_an_1.R;
import com.example.admin.du_an_1.UI.Fragment.Fragment_List;
import com.example.admin.du_an_1.UI.Fragment.Fragment_Statitic;
import com.example.admin.du_an_1.UI.Fragment.Fragment_User;

public class MainActivity extends AppCompatActivity {
    private TabAdapter tabAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);

        tabAdapter= new TabAdapter(getSupportFragmentManager());
        tabAdapter.addFragment(new Fragment_List(), "Storage");
        tabAdapter.addFragment(new Fragment_User(), "Users");
        tabAdapter.addFragment(new Fragment_Statitic(), "Statistics");
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);

        final Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.menu,menu  );
        return super.onCreateOptionsMenu( menu );
    }


    //////////////////////////////
//// PUSH ITEMS IN ACTION_BAR. //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.listCat:
                startActivity( new Intent( MainActivity.this, CategoryActivity.class ) );
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
