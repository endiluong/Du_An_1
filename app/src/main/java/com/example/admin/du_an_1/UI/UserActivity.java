package com.example.admin.du_an_1.UI;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.du_an_1.Adapter.UserAdapter;
import com.example.admin.du_an_1.DAO.daoUsers;
import com.example.admin.du_an_1.R;
import com.example.admin.du_an_1.Repository.Users;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity{
    ListView lvUser;
    FloatingActionButton fab;
    public static List<Users> usersList = new ArrayList<>();
    UserAdapter adapter = null;
    daoUsers daoUsers;
    Context context;
    Users users,usersdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        context = getBaseContext();
        daoUsers = com.example.admin.du_an_1.DAO.daoUsers.getInstance(this);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvUser = (ListView) findViewById(R.id.lvUser);
        fab = (FloatingActionButton) findViewById(R.id.btnFAB_AddUser);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddUserActivity.class));
            }
        });

        lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                List<Users> list = daoUsers.getAllItem();
                //
                 usersdialog = list.get(i);
                //
                final Dialog dialog = new Dialog(UserActivity.this);
                dialog.setTitle("INFO USER");
                dialog.setContentView(R.layout.item_option_user);
                dialog.setCancelable(false);
                //
                TextView tvName = (TextView) dialog.findViewById(R.id.tvUserNameOption);
                TextView tvPass = (TextView) dialog.findViewById(R.id.tvPassWordOption);
                Button btnDelete = (Button) dialog.findViewById(R.id.btnDelete);
                Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);

                tvName.setText(usersdialog.getUserName());
                tvPass.setText(usersdialog.getPassword());

/////////////////////////--------Delete--------////////////////////////////////////
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                        builder.setTitle("DELETE")
                                .setMessage("\n" +
                                        "Are you sure you want to delete " + usersList.get(i).getUserName())
                                .setCancelable(true)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        int result;
                                        if (usersdialog != null) {
                                            String nametemp = usersdialog.getUserName();
                                            result = daoUsers.deleteUserbyUN(usersdialog);
                                            if (result > 0) {
                                                Toast.makeText(getBaseContext(), nametemp+ " Was Deleted", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(getBaseContext(), "Delete failed", Toast.LENGTH_SHORT).show();
                                            }
                                            startActivityForResult( new Intent( getBaseContext(),UserActivity.class ),0 );
                                            finish();
                                        }
                                        dialog.cancel();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialog.dismiss();
                                        dialog.cancel();
                                    }
                                }).create().show();
                    }
                });
///////////////////----------Cancel-----------///////////////////////
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public void onResume() {
        usersList = daoUsers.getAllItem();
        adapter = new UserAdapter(context, usersList);
        lvUser.setAdapter(adapter);
        lvUser.deferNotifyDataSetChanged();
        super.onResume();
    }

}

