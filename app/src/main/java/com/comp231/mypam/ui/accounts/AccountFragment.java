package com.comp231.mypam.ui.accounts;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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

import com.comp231.mypam.AccountInfo;
import com.comp231.mypam.AccountItemAdapterListView;
import com.comp231.mypam.R;
import com.comp231.mypam.database.DataSource;
import com.comp231.mypam.model.Account;
import com.comp231.mypam.sample.SampleDataProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AccountFragment extends Fragment {

    private AccountViewModel slideshowViewModel;

   List<Account> accountsList = SampleDataProvider.accountItemList;
    DataSource mDataSource;
    private Context mContext;
    View myView;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(AccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        myView = root;
        final TextView textView = root.findViewById(R.id.textView);
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        //creating the database
        mDataSource = new DataSource(mContext);
        mDataSource.open();
        mDataSource.seedDataBaseAccounts(accountsList);

        final List<Account> accountListFromDb = mDataSource.getAllAccounts();
        List<String> itemNames = new ArrayList<>();

        for (Account item: accountListFromDb) {
            itemNames.add(item.getAccountName());
        }

        Collections.sort(accountListFromDb, new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                return o1.getAccountName().compareTo(o2.getAccountName());
            }});

        AccountItemAdapterListView accountAdapter = new AccountItemAdapterListView(mContext,accountListFromDb);

        ListView listView = root.findViewById(android.R.id.list);

        listView.setAdapter((ListAdapter) accountAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent intent = new Intent(mContext, AccountInfo.class);
                String message = null;
                try {
                    message = accountListFromDb.get(arg2).getAccountId();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                intent.putExtra("account", message);
                startActivity(intent);
            }

        });

        FloatingActionButton fabAdd = root.findViewById(R.id.fabAdd);

        fabAdd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent i = new Intent(mContext, AccountInfo.class);
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
}
