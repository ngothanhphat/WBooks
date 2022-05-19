package com.lap_trinh_android.wbooks.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lap_trinh_android.wbooks.R;
import com.lap_trinh_android.wbooks.model.Account;

import java.util.ArrayList;
import java.util.List;

public class AdapterInformation extends BaseAdapter {

    private Context context;
    private  int layout;

    private List<Account> AccountList;

    public AdapterInformation(Context context, int layout, List<Account> accountList) {
        this.context = context;
        this.layout = layout;
        AccountList = accountList;
    }

    @Override
    public int getCount() {
        return AccountList.size();
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        TextView txtNameAccount = (TextView) convertView.findViewById(R.id.text_name);
        TextView txtEmail = (TextView) convertView.findViewById(R.id.text_email);

        Account account = AccountList.get(position);

        txtNameAccount.setText(account.getmNameAccount());
        txtEmail.setText(account.getmEmail());

        return convertView;
    }
}
