package com.comp231.mypam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.comp231.mypam.model.Account;
import com.comp231.mypam.model.Category;

import java.util.List;

public class AccountItemAdapterListView extends ArrayAdapter<Account> {

    private List<Account> mAccount;
    private LayoutInflater mInflater;
    private Context mContext;
    public TextView tvName;
    public View mView;

    public AccountItemAdapterListView(@NonNull Context context, @NonNull List<Account> objects) {

        super(context, R.layout.activity_ru05_account, objects);
        mAccount = objects;
        mInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(android.R.layout.simple_list_item_1,parent,false);
        }

        TextView tvName = convertView.findViewById(android.R.id.text1);

        Account account = mAccount.get(position);
        tvName.setText(account.getAccountName());
        return convertView;
    }



}
