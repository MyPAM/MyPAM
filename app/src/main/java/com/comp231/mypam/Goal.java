package com.comp231.mypam;


public class Goal {
    private String goalName;
    private String targetDate;
    private double cost;
    public Goal() {
        this.goalName = "";
        this.targetDate = "";
        this.cost = 0/0; }

    public Goal(String goalName, String targetDate, double cost) {
        this.goalName = goalName;
        this.targetDate = targetDate; this.cost = cost; }

        public String getGoalName() {
        return goalName; }

    public void setGoalName(String goalName) {
        this.goalName = goalName; }

    public String getTargetDate() {
        return targetDate; }

        public void setTargetDate(String targetDate) {
        this.targetDate = targetDate; }
        public double getCost() {
        return cost; }

        public void setCost(double cost) {
        this.cost = cost; }
        public String toString() {
        return(getGoalName() + " " + getTargetDate() + " $" + String.format("%,.2f", getCost())); }
}
