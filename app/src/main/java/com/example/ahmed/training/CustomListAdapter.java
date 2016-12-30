package com.example.ahmed.training;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ahmed on 12/30/2016.
 */

public class CustomListAdapter extends ArrayAdapter<Task> {
    private Task mCurrentTask;
    private Context mContext;
    private TextView mTitleTextView;


    public CustomListAdapter(Context context, int resource, List<Task> objects) {
        super(context, resource, objects);
        mContext = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        mCurrentTask = getItem(position);
        View view = convertView;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.task_list_item, null);
        }

        mTitleTextView = (TextView) view.findViewById(R.id.list_item_title);
        mTitleTextView.setText(mCurrentTask.getTitle());
        return view;
    }
}
