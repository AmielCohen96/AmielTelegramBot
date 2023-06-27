package org.example;

public class Action {
    private String userName;
    private String date;
    private String activityName;

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User: |" + userName +
                "| Date: |" + date +
                "| Activity: |" + activityName+"|";
    }


}
