package com.comp231.mypam.ui.categories;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.comp231.mypam.CategoryActivity;
import com.comp231.mypam.DataItemAdapterListView;
import com.comp231.mypam.R;
import com.comp231.mypam.database.DataSource;
import com.comp231.mypam.model.Category;
import com.comp231.mypam.sample.SampleDataProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CategoryFragment extends Fragment {

    private CategoryViewModel galleryViewModel;

    List<Category> categoryList = SampleDataProvider.categoryItemList;
    DataSource mDataSource;
    private Context mContext;
    View myView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(CategoryViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_category, container, false);
        myView = root;
        final TextView textView = root.findViewById(R.id.text_gallery);
        root.setTag("myView");
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        //creating the database
        mDataSource = new DataSource(mContext);
        mDataSource.open();
        mDataSource.seedDataBase(categoryList);

        final List<Category> listFromDb = mDataSource.getAllItems();
        List<String> itemNames = new ArrayList<>();

        for (Category item: listFromDb) {
            itemNames.add(item.getCategoryName());
        }

        Collections.sort(listFromDb, new Comparator<Category>() {
            @Override
            public int compare(Category o1, Category o2) {
                return o1.getCategoryName().compareTo(o2.getCategoryName());
            }});

        DataItemAdapterListView adapter = new DataItemAdapterListView(mContext,listFromDb);

        if (root.findViewById(android.R.id.list) == null) {
            Log.i("test ", "nulo!");
        }

        ListView listView = root.findViewById(android.R.id.list);

        listView.setAdapter((ListAdapter) adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent intent = new Intent(mContext, CategoryActivity.class);
                String message = null;
                try {
                    message = listFromDb.get(arg2).getCategoryId();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                intent.putExtra("category", message);
                startActivity(intent);
            }

        });

        FloatingActionButton fabAdd = root.findViewById(R.id.fabAdd);

        fabAdd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent i = new Intent(mContext, CategoryActivity.class);
                i.putExtra("add", "add");
//                startActivity(i);
                getActivity().startActivityForResult(i,10);
            }
        });



        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onResume() {
        super.onResume();

        //creating the database
        mDataSource = new DataSource(mContext);
        mDataSource.open();
        mDataSource.seedDataBase(categoryList);

        final List<Category> listFromDb = mDataSource.getAllItems();
        List<String> itemNames = new ArrayList<>();

        for (Category item: listFromDb) {
            itemNames.add(item.getCategoryName());
        }

        Collections.sort(listFromDb, new Comparator<Category>() {
            @Override
            public int compare(Category o1, Category o2) {
                return o1.getCategoryName().compareTo(o2.getCategoryName());
            }});


        DataItemAdapterListView adapter = new DataItemAdapterListView(mContext,listFromDb);

        ListView listView = myView.findViewById(android.R.id.list);

        listView.setAdapter((ListAdapter) adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent intent = new Intent(mContext, CategoryActivity.class);
                String message = null;
                try {
                    message = listFromDb.get(arg2).getCategoryId();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                intent.putExtra("category", message);
                startActivity(intent);
            }

        });

        FloatingActionButton fabAdd = myView.findViewById(R.id.fabAdd);

        fabAdd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent i = new Intent(mContext, CategoryActivity.class);
                i.putExtra("add", "add");
//                startActivity(i);
                getActivity().startActivityForResult(i,10);
            }
        });

    }
}
