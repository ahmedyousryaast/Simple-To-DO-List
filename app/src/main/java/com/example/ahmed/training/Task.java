package com.example.ahmed.training;

import java.io.Serializable;

/**
 * Created by Ahmed on 12/30/2016.
 */

public class Task implements Serializable {
    private String mTitle, mDescription, mIndex;
    private boolean isCompleted;

    public Task() {

    }


    public Task(String title, String description, boolean isCompleted) {
        mTitle = title;
        mDescription = description;
        this.isCompleted = isCompleted;

    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "Task{" +
                "mTitle='" + mTitle + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mIndex='" + mIndex + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
