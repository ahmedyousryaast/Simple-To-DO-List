package com.example.ahmed.training;

import android.support.v4.app.Fragment;

/**
 * Created by Ahmed on 12/30/2016.
 */

public class TaskDetailsActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return new TaskDetailsFragment();
    }
}
