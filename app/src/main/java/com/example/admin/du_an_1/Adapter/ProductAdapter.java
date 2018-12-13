package com.example.admin.du_an_1.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.admin.du_an_1.DAO.daoTicket;
import com.example.admin.du_an_1.DAO.daoProducts;
import com.example.admin.du_an_1.R;
import com.example.admin.du_an_1.Repository.Product;
import com.example.admin.du_an_1.Repository.Ticket;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class ProductAdapter extends BaseAdapter {
    List<Product> arrProduct;
    List<Product> arrTicket;
    Context context;
    LayoutInflater inflater;
    daoProducts daoProducts;
    private List<Product> arrProductPhu;
    Ticket tempp;
    daoTicket DaoTicket;

    public ProductAdapter(Context context, List<Product> arrProduct){
        super();
        this.context = context ;
        this.arrProduct = arrProduct;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        daoProducts = new daoProducts(context);
        DaoTicket = new daoTicket(context);

        this.arrProductPhu = new ArrayList<Product>();
        this.arrProductPhu.addAll(arrProduct);

    }
    // tim kiem
// thay doi list
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        arrProduct.clear();
        if (charText.length() == 0)
        {
            arrProduct.addAll(arrProductPhu);
        }
        else {
            for (Product  Name : arrProductPhu){
                String biendoi = remoAccent(Name.getName().toLowerCase(Locale.getDefault()));
                if (biendoi.contains(charText)){
                    arrProduct.add(Name);
                }
            }
        }
        notifyDataSetChanged();
    }

    // chuyen chu co dau thanh khong dau
    public static String remoAccent(String s){
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
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
        TextView tvquan;
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
            holder.tvquan = (TextView) convertview.findViewById(R.id.tvsoluongadp);
            convertview.setTag(holder);
        } else
            holder = (ViewHolder) convertview.getTag();
        Product _entry = (Product) arrProduct.get(i);
        holder.tvProName.setText(_entry.getName());
        holder.tvProCode.setText(_entry.getCode());
        holder.tvProCat.setText(_entry.getCategory());

        tempp = new Ticket();
        tempp = DaoTicket.getByName(_entry.getName());
        holder.tvquan.setText(String.valueOf(tempp.getQuantity()));
        return convertview;
    }

//    @Override
//    public void notifyDataSetChanged() {
//        super.notifyDataSetChanged();
//    }
//    public void changeDataset(List<Product> items){
//        this.arrProduct = items;
//        notifyDataSetChanged();
//    }
}
