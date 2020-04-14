package com.comp231.mypam.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.comp231.mypam.AccountItemAdapterListView;
import com.comp231.mypam.DataItemAdapterListView;
import com.comp231.mypam.R;
import com.comp231.mypam.database.DataSource;
import com.comp231.mypam.model.Account;
import com.comp231.mypam.model.Category;
import com.comp231.mypam.model.Entry;
import com.comp231.mypam.sample.SampleDataProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    List<Category> categoryList = SampleDataProvider.categoryItemList;
    List<Account> accountsList = SampleDataProvider.accountItemList;
    List<Entry> entryList = SampleDataProvider.entryList;
    DataSource mDataSource;
    private Context mContext;
    View myView;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        //creates and initialize all databases
        mDataSource = new DataSource(mContext);
        mDataSource.open();
        mDataSource.seedDataBaseAccounts(accountsList);
        mDataSource.seedDataBase(categoryList);
        mDataSource.seedDataBaseEntries(entryList);


        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
