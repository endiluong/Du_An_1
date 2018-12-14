package com.example.admin.du_an_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.du_an_1.DAO.daoUsers;
import com.example.admin.du_an_1.R;
import com.example.admin.du_an_1.Repository.Users;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    List<Users> arrUsers;
    Context context;
    LayoutInflater inflater;
    daoUsers myDao;

    public UserAdapter(Context context,List<Users> arrUsers){
        super();
        this.context = context;
        this.arrUsers = arrUsers;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        myDao = new daoUsers(context);
    }


    @Override
    public int getCount()           { return arrUsers.size(); }

    @Override
    public Object getItem(int i)    { return arrUsers.get(i); }

    @Override
    public long getItemId(int i)    { return 0; }
    public static class ViewHolder{
        TextView txtUserName;
//        TextView txtPassUser;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = new ViewHolder();
        if (view == null){
            view = inflater.inflate(R.layout.item_user,null);
            holder.txtUserName = (TextView) view.findViewById(R.id.tvUser_Name);
//            holder.txtPassUser = (TextView) view.findViewById(R.id.tvPass_User);
            view.setTag(holder);
        } else
            holder = (ViewHolder)view.getTag();
        Users temp = (Users) arrUsers.get(i);
        holder.txtUserName.setText(temp.getUserName());
//        holder.txtPassUser.setText(temp.getId());
        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataset(List<Users> items){
        this.arrUsers = items;
        notifyDataSetChanged();
    }
}
