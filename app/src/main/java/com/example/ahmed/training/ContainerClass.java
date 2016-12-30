package com.example.ahmed.training;

import java.io.Serializable;

/**
 * Created by Ahmed on 12/30/2016.
 */

public class ContainerClass implements Serializable {
    Task mTask;
    String key;

    public ContainerClass(Task task, String key) {
        mTask = task;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public Task getTask() {
        return mTask;
    }

}
