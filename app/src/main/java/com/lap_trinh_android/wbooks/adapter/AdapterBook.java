package com.lap_trinh_android.wbooks.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lap_trinh_android.wbooks.R;
import com.lap_trinh_android.wbooks.model.Book;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterBook extends BaseAdapter {

    private Context context;
    private ArrayList<Book> listBook;

    public AdapterBook(Context context, ArrayList<Book> listBook) {
        this.context = context;
        this.listBook = listBook;
    }

    @Override
    public int getCount() {
        return listBook.size();
    }

    @Override
    public Object getItem(int position) {
        return listBook.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

//    Filter
    public void filterList(ArrayList<Book> filteredList) {
        listBook = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder {
        TextView txtBookName;
        ImageView imgBook;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        viewHolder = new ViewHolder();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.new_book, null);

        viewHolder.txtBookName = convertView.findViewById(R.id.txt_new_book_name);
        viewHolder.imgBook = convertView.findViewById(R.id.img_new_book);
        convertView.setTag(viewHolder);

        Book book = (Book) getItem(position);
        viewHolder.txtBookName.setText(book.getBookName());

        Picasso.get().load(book.getImage()).placeholder(R.drawable.ic_load).error(R.drawable.ic_image).into(viewHolder.imgBook);

        return convertView;
    }
}
