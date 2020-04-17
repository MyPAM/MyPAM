package com.comp231.mypam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GoalAdapter
        extends RecyclerView.Adapter<GoalAdapter.GoalViewHolder> {
    private Context context;
    private ArrayList<Goal> myGoals;
    public GoalAdapter(Context context, ArrayList<Goal> myGoals) {
        this.context = context;
        this.myGoals = new ArrayList<>();
        this.myGoals = myGoals; }

        @NonNull @Override
        public GoalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.goal_list_item, parent, false);
        return new GoalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoalViewHolder holder, int position) {
        String res = myGoals.get(position).toString();
        holder.goalListItemDesc.setText(res);
    }

    @Override
    public int getItemCount() {
        return myGoals.size();
    }
        public class GoalViewHolder extends RecyclerView.ViewHolder {
        TextView goalListItemDesc;
        public GoalViewHolder(@NonNull View itemView) {
            super(itemView);
            goalListItemDesc = (TextView) itemView.findViewById(R.id.goalListItemDesc); } } }