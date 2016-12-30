package com.example.ahmed.training;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Ahmed on 12/30/2016.
 */

public class AddTaskFragment extends Fragment implements View.OnClickListener {
    private EditText mTitleText, mDescriptionText;
    private Button mButton;
    private DatabaseReference mRootRef;
    private Task mTask;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_task_fragment, container, false);
        mTitleText = (EditText) view.findViewById(R.id.task_title);
        mDescriptionText = (EditText) view.findViewById(R.id.task_description);
        mButton = (Button) view.findViewById(R.id.add_task_to_db);
        mRootRef = FirebaseDatabase.getInstance().getReference();


        mButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {


        String title = mTitleText.getText().toString().trim();
        String desc = mDescriptionText.getText().toString().trim();
        mTask = new Task(title, desc, false);
        mRootRef.push().setValue(mTask);
        Toast.makeText(getActivity(), "Task Added", Toast.LENGTH_LONG).show();

        getActivity().finish();

    }
}
