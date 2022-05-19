package com.lap_trinh_android.wbooks.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lap_trinh_android.wbooks.R;
import com.lap_trinh_android.wbooks.model.Categories;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterCategories extends BaseAdapter {

    private Context context;
    private  int layout;

    private List<Categories> categoriesList;

    public AdapterCategories(Context context, int layout, List<Categories> categoriesList) {
        this.context = context;
        this.layout = layout;
        this.categoriesList = categoriesList;
    }

    @Override
    public int getCount() {
        return categoriesList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(layout, null);

        ImageView img = (ImageView) convertView.findViewById(R.id.img_category);
        TextView txt = (TextView) convertView.findViewById(R.id.txt_category_name);
        Categories cg = categoriesList.get(position);

        txt.setText(cg.getCategory_name());

        Picasso.get().load(cg.getCategory_image()).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(img);
        return convertView;
    }
}
