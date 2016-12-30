package com.example.ahmed.training;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Ahmed on 12/30/2016.
 */

public class TaskDetailsFragment extends Fragment {
    private static final String EXTRA_ARGS = "ContainerExtraString";
    private ContainerClass mContainer;
    private EditText mTitleText, mDescribtionText;
    private CheckBox mIsCompleted;
    private Button mSaveButton;
    private DatabaseReference mDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_details_layout, container, false);
        setHasOptionsMenu(true);

        mContainer = (ContainerClass) getActivity().getIntent().getSerializableExtra(EXTRA_ARGS);

        mTitleText = (EditText) view.findViewById(R.id.task_details_title);
        mTitleText.setText(mContainer.getTask().getTitle());

        mDescribtionText = (EditText) view.findViewById(R.id.task_details_description);
        mDescribtionText.setText(mContainer.getTask().getDescription());

        mIsCompleted = (CheckBox) view.findViewById(R.id.task_details_ceck_box);
        mIsCompleted.setChecked(mContainer.getTask().isCompleted());


        mSaveButton = (Button) view.findViewById(R.id.save_button);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateInDB();
            }
        });

        return view;
    }

    private void updateInDB() {
        mContainer.getTask().setTitle(mTitleText.getText().toString().trim());
        mContainer.getTask().setDescription(mDescribtionText.getText().toString().trim());
        mContainer.getTask().setCompleted(mIsCompleted.isChecked());
        mDatabase.child(mContainer.getKey()).setValue(mContainer.getTask());

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.deletetask, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.menu_item_delete_task:
                mDatabase.child(mContainer.getKey()).removeValue();
                Toast.makeText(getActivity(), "Task Removed", Toast.LENGTH_SHORT).show();
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
