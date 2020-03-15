package com.comp231.mypam;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<MemberDTO> memberDTOs = new ArrayList<>();

    public RecyclerViewAdapter() {
        memberDTOs.add(new MemberDTO());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_entry,parent,false);

        return new RowCell(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((RowCell)holder).addButton.setImageResource(memberDTOs.get(position).addButtonImage);
        ((RowCell)holder).category.setHint(memberDTOs.get(position).category);
        ((RowCell)holder).date.setHint(memberDTOs.get(position).date);
        ((RowCell)holder).amount.setHint(memberDTOs.get(position).amount);
        ((RowCell)holder).removeButton.setImageResource(memberDTOs.get(position).removeButtonImage);
    }

    @Override
    public int getItemCount() {
        return memberDTOs.size();
    }

    private static class RowCell extends RecyclerView.ViewHolder {
        FloatingActionButton addButton;
        EditText category;
        EditText date;
        EditText amount;
        FloatingActionButton removeButton;
        public RowCell(View view) {
            super(view);
            addButton = (FloatingActionButton)view.findViewById(R.id.addFloatingActionButton);
            category = (EditText)view.findViewById(R.id.categoryEditText);
            date = (EditText)view.findViewById(R.id.dateEditText);
            amount = (EditText)view.findViewById(R.id.amountEditText);
            removeButton = (FloatingActionButton)view.findViewById(R.id.removeFloatingActionButton);
        }
    }
}
