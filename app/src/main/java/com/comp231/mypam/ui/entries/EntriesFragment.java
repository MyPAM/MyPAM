package com.comp231.mypam.ui.entries;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.comp231.mypam.CategoryActivity;
import com.comp231.mypam.DataItemAdapterListView;
import com.comp231.mypam.EntryActivity;
import com.comp231.mypam.EntryItemAdapterListView;
import com.comp231.mypam.R;
import com.comp231.mypam.R.layout.*;
import com.comp231.mypam.database.DataSource;
import com.comp231.mypam.model.Category;
import com.comp231.mypam.model.Entry;
import com.comp231.mypam.sample.SampleDataProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EntriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EntriesFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private double mTotal;

    public EntriesFragment() {
        // Required empty public constructor
    }


    //gets the current date
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    String currentDateTime = sdf.format(new Date());

    //Gets the data from the sample to populate the database, if empty
    List<Entry> entryList = SampleDataProvider.entryList;
    DataSource mDataSource;
    private Context mContext;
    View myView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EntriesFragment.
     */

    public static EntriesFragment newInstance(String param1, String param2) {
        EntriesFragment fragment = new EntriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView =  inflater.inflate(R.layout.fragment_entries, container, false);

        //creating the database
        mDataSource = new DataSource(mContext);
        mDataSource.open();
        mDataSource.seedDataBaseEntries(entryList);

        final List<Entry> entryListFromDb = mDataSource.getAllEntries();
        List<String> entriesNames = new ArrayList<>();

        Collections.sort(entryListFromDb, new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                return o1.getEntryDate().compareTo(o2.getEntryDate());
            }});

        //poupulating the gridview
        List<String> values=new ArrayList<String>();
        values.add("Date");values.add("Entry");values.add("Amount");

        mTotal = 0;
        for (Entry item: entryListFromDb) {
            //selects the entries from the current month
            if (item.getEntryDate().substring(3,10).equals(currentDateTime.substring(3,10))){
                entriesNames.add(item.getEntryDescription());
                String amount = String.format("%.2f",item.getAmount());
                //format negative values
                if (item.getEntryType().equals("D")) {
                    amount = String.format("(%s)",amount);
                    mTotal = mTotal - item.getAmount();
                } else {
                    mTotal = mTotal + item.getAmount();
                }
                values.add(item.getEntryDate());values.add(item.getEntryDescription());values.add(amount);
            }
        }

        TextView tvTotal = myView.findViewById(R.id.tvTotal);
        if (mTotal < 0 ) {
            tvTotal.setText(String.format("Total Balance: (%.2f)",mTotal));
        } else {
            tvTotal.setText(String.format("Total Balance: %.2f",mTotal));
        }

        GridView myGrid=(GridView) myView.findViewById(android.R.id.list);

        myGrid.setAdapter(new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,values));

        myGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent intent = new Intent(mContext, EntryActivity.class);
                String message = null;
                try {
                    message = entryListFromDb.get(arg2).getEntryId();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                intent.putExtra("entry", message);
                startActivity(intent);
            }

        });

        //event handler for the Add New Entry Button
        FloatingActionButton fabAdd = myView.findViewById(R.id.fabAdd);

        fabAdd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent i = new Intent(mContext, EntryActivity.class);
                i.putExtra("add", "add");
                getActivity().startActivityForResult(i,10);
            }
        });

        return myView;

    }

    @Override
    public void onResume() {
        super.onResume();

        //creating the database
        mDataSource = new DataSource(mContext);
        mDataSource.open();
        mDataSource.seedDataBaseEntries(entryList);

        final List<Entry> entryListFromDb = mDataSource.getAllEntries();
        List<String> entryNames = new ArrayList<>();

        for (Entry item: entryListFromDb) {
            entryNames.add(item.getEntryDescription());
        }

        Collections.sort(entryListFromDb, new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                return o1.getEntryDate().compareTo(o2.getEntryDate());
            }});

        //populating the gridview
        final List<String> values=new ArrayList<String>();
        values.add("Date");values.add("Entry");values.add("Amount");
        final List<String> valuesIds=new ArrayList<String>();
        valuesIds.add("Date");valuesIds.add("Entry");valuesIds.add("Amount");

        mTotal = 0;
        for (Entry item: entryListFromDb) {
            if (item.getEntryDate().substring(3,10).equals(currentDateTime.substring(3,10))){
                 String amount = String.format("%.2f",item.getAmount());
                    //formats debit type
                    if (item.getEntryType().equals("D")) {
                        amount = String.format("(%s)", amount);
                        mTotal = mTotal - item.getAmount();
                    } else {
                        mTotal = mTotal + item.getAmount();
                    }

                values.add(item.getEntryDate());values.add(item.getEntryDescription());values.add(amount);
                valuesIds.add(item.getEntryId());valuesIds.add(item.getEntryId());valuesIds.add(item.getEntryId());
            }
        }

        TextView tvTotal = myView.findViewById(R.id.tvTotal);
        if (mTotal < 0 ) {
            tvTotal.setText(String.format("Total Balance: (%.2f)",mTotal));
        } else {
            tvTotal.setText(String.format("Total Balance: %.2f",mTotal));
        }


        GridView myGrid=(GridView) myView.findViewById(android.R.id.list);

        myGrid.setAdapter(new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,values));

        myGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent intent = new Intent(mContext, EntryActivity.class);
                String message = null;
                try {
                    message = valuesIds.get(arg2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                intent.putExtra("entry", message);
                startActivity(intent);
            }

        });

        FloatingActionButton fabAdd = myView.findViewById(R.id.fabAdd);

        fabAdd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent i = new Intent(mContext, EntryActivity.class);
                i.putExtra("add", "add");
                getActivity().startActivityForResult(i,10);
            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

}
