package com.example.admin.du_an_1.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.du_an_1.DAO.daoProducts;
import com.example.admin.du_an_1.R;
import com.example.admin.du_an_1.Repository.Product;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    List<Product> arrProduct;
    Context context;
    LayoutInflater inflater;
    daoProducts daoProducts;

    public ProductAdapter(Context context, List<Product> arrProduct){
        super();
        this.context = context ;
        this.arrProduct = arrProduct;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        daoProducts = new daoProducts(context);
    }
    @Override
    public int getCount() {
        return arrProduct.size();
    }

    @Override
    public Object getItem(int i) {
        return arrProduct.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public static class ViewHolder{
        TextView tvProName;
        TextView tvProCode;
        TextView tvProCat;
    }

    @Override
    public View getView(int i, View convertview, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertview == null){
            holder = new ViewHolder();
            convertview = inflater.inflate(R.layout.item_product,null);
            holder.tvProName = (TextView) convertview.findViewById(R.id.tvProName);
            holder.tvProCode = (TextView) convertview.findViewById(R.id.tvProCode);
            holder.tvProCat = (TextView) convertview.findViewById(R.id.tvProCat);
            convertview.setTag(holder);
        } else
            holder = (ViewHolder) convertview.getTag();
        Product _entry = (Product) arrProduct.get(i);
        holder.tvProName.setText(_entry.getName());
        holder.tvProCode.setText(_entry.getCode());
        holder.tvProCat.setText(_entry.getCategory());
        return convertview;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataset(List<Product> items){
        this.arrProduct = items;
        notifyDataSetChanged();
    }
}
