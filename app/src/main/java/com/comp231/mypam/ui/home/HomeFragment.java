package com.comp231.mypam.ui.home;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    List<Category> categoryList = SampleDataProvider.categoryItemList;
    List<Account> accountsList = SampleDataProvider.accountItemList;
    List<Entry> entryList = SampleDataProvider.entryList;
    DataSource mDataSource;
    private Context mContext;
    View myView;

    //gets the current date
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    String currentDateTime = sdf.format(new Date());

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //declare the chart graph
        PieChartView pieChartView = root.findViewById(R.id.chart);

        //creates and initialize all databases
        mDataSource = new DataSource(mContext);
        mDataSource.open();
        mDataSource.seedDataBaseAccounts(accountsList);
        mDataSource.seedDataBase(categoryList);
        mDataSource.seedDataBaseEntries(entryList);

        int [] arrayColors={Color.rgb(115, 163, 175),
                            Color.rgb(222, 182, 241),
                            Color.rgb(222, 137, 175),
                            Color.rgb(72, 182, 241),
                            Color.rgb(188, 22, 69),
                            Color.rgb(192, 254, 136),
                            Color.rgb(140, 162, 168),
                            Color.RED,Color.MAGENTA,Color.GRAY,Color.YELLOW,Color.LTGRAY,Color.GREEN};
        int indx = 0;
        //gets the entries from database
        //final List<Entry> entryListFromDb = mDataSource.getEntryByDate(currentDateTime.substring(0,10));

        Log.i("test",currentDateTime.substring(0,10));

        final List<Entry> entryListFromDb = mDataSource.getEntryByDate(currentDateTime.substring(0,10));

        //initialize the data for the graph
        List<SliceValue> pieData = new ArrayList<>();

        //move the entries to the graph
        for (Entry item: entryListFromDb) {
            pieData.add(new SliceValue((int)item.getAmount(), arrayColors[indx]).setLabel(item.getEntryDescription() + " " + item.getAmount()));
            indx = indx +1;
        }

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);
        pieChartView.setPieChartData(pieChartData);

        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
