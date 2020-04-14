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
import com.comp231.mypam.model.Entry;

import java.util.List;

public class EntryItemAdapterListView extends ArrayAdapter<Entry> {

    private List<Entry> mEntry;
    private LayoutInflater mInflater;
    private Context mContext;
    public TextView tvName;
    public View mView;

    public EntryItemAdapterListView(@NonNull Context context, @NonNull List<Entry> objects) {

        super(context, R.layout.activity_entry, objects);
        mEntry = objects;
        mInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(android.R.layout.simple_list_item_1,parent,false);
        }

        TextView tvName = convertView.findViewById(android.R.id.text1);

        Entry entry = mEntry.get(position);
        tvName.setText(entry.getEntryDescription());
        return convertView;
    }



}
