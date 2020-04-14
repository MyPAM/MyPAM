package com.comp231.mypam.ui.entries;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.comp231.mypam.EntryItemAdapterListView;
import com.comp231.mypam.R;
import com.comp231.mypam.R.layout.*;
import com.comp231.mypam.database.DataSource;
import com.comp231.mypam.model.Entry;
import com.comp231.mypam.sample.SampleDataProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public EntriesFragment() {
        // Required empty public constructor
    }

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

        //poupulating the gridview
        List<String> values=new ArrayList<String>();
        values.add("Date");values.add("Entry");values.add("Amount");

        for (Entry item: entryListFromDb) {
            entriesNames.add(item.getEntryDescription());
            values.add(item.getEntryDate());values.add(item.getEntryDescription());values.add(String.valueOf(item.getAmount()));
        }

        Collections.sort(entryListFromDb, new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                return o1.getEntryDescription().compareTo(o2.getEntryDescription());
            }});

        GridView myGrid=(GridView) myView.findViewById(android.R.id.list);
        //View test = myView.findViewById(R.id.cell);
        myGrid.setAdapter(new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,values));
        return myView;

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
