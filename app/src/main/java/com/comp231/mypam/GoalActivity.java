package com.comp231.mypam;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GoalActivity extends AppCompatActivity {
    private RecyclerView goalsRecyclerView;
    private EditText goalField, dateField, costField;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
        goalField = (EditText) findViewById(R.id.goalField);
        dateField = (EditText) findViewById(R.id.dateField);
        costField = (EditText) findViewById(R.id.costField);
        goalsRecyclerView = (RecyclerView) findViewById(R.id.goalsRecyclerView);
        saveButton = (Button) findViewById(R.id.saveButton);
        final ArrayList<Goal> myGoals = new ArrayList<>();
        final GoalAdapter adapter = new GoalAdapter(GoalActivity.this, myGoals);
        goalsRecyclerView.setLayoutManager(new LinearLayoutManager(GoalActivity.this));
        goalsRecyclerView.setAdapter(adapter);
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View v) {
                String goalName = goalField.getText().toString().trim();
                String targetDate = dateField.getText().toString().trim();
                String costEst = costField.getText().toString().trim();
                if(goalName.isEmpty() || targetDate.isEmpty() || costEst.isEmpty()) {
                    Toast.makeText(GoalActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                    return; } double cost = Double.parseDouble(costEst); // search if the goal name exists, if exists, edit the fields, else add a new goal

                int index = checkGoal(myGoals, goalName); if(index == -1) { // add a new
                     myGoals.add(new Goal(goalName, targetDate, cost)); }
                     else { // edit the already existing goal
                         myGoals.get(index).setTargetDate(targetDate);
                         myGoals.get(index).setCost(cost); } goalsRecyclerView.setAdapter(adapter);
                     adapter.notifyDataSetChanged(); goalField.setText(""); dateField.setText("");
                     costField.setText("");
            } });
    }
    private int checkGoal(ArrayList<Goal> goals, String goalName) {
        int index = -1;
        for(int i = 0;
            i < goals.size();
            i++) {
            if(goals.get(i).getGoalName().equalsIgnoreCase(goalName)) {
                index = i; break;
            } }
        return index; } }