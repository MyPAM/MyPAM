package com.comp231.mypam;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.comp231.mypam.model.Category;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DataItemAdapterListView extends ArrayAdapter<Category> {

    private List<Category> mCategory;
    private LayoutInflater mInflater;
    private Context mContext;
    public TextView tvName;
    public View mView;

    public DataItemAdapterListView(@NonNull Context context, @NonNull List<Category> objects) {

        super(context, R.layout.activity_category, objects);
        mCategory = objects;
        mInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(android.R.layout.simple_list_item_1,parent,false);
        }

        TextView tvName = convertView.findViewById(android.R.id.text1);

        Category category = mCategory.get(position);
        tvName.setText(category.getCategoryName());
        return convertView;
    }



}
