package com.example.ahmed.training;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Ahmed on 12/30/2016.
 */

public class TasksListFragment extends Fragment {
    private ListView mListView;
    private CustomListAdapter mCustomListAdapter;
    private ArrayList<Task> mTaskArrayList;
    private ArrayList<ContainerClass> mContainerClasses;
    private DatabaseReference mRootReference;
    private static final String EXTRA_ARGS = "ContainerExtraString";

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_list_fragment, container, false);
        setHasOptionsMenu(true);
        mTaskArrayList = new ArrayList<>();
        mContainerClasses = new ArrayList<>();


        //root reference
        mRootReference = FirebaseDatabase.getInstance().getReference();
        mRootReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //getting all children tasks
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                //itertating throw the list
                if (mTaskArrayList == null) {
                    for (DataSnapshot child : children) {
                        Task value = child.getValue(Task.class);
                        mContainerClasses.add(new ContainerClass(value, child.getKey()));
                        mTaskArrayList.add(value);
                        mCustomListAdapter.notifyDataSetChanged();
                    }
                } else {
                    mTaskArrayList.clear();
                    mContainerClasses.clear();
                    for (DataSnapshot child : children) {
                        Task value = child.getValue(Task.class);
                        mContainerClasses.add(new ContainerClass(value, child.getKey()));
                        mTaskArrayList.add(value);
                    }
                    mCustomListAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mCustomListAdapter = new CustomListAdapter(getActivity(), R.layout.task_list_item, mTaskArrayList);

        mListView = (ListView) view.findViewById(R.id.tasks_list_view);

        mListView.setAdapter(mCustomListAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), TaskDetailsActivity.class);
                Log.d("test", "" + i);
                Log.d("test 2", mContainerClasses.get(i).getKey());
                intent.putExtra(EXTRA_ARGS, mContainerClasses.get(i));
                startActivity(intent);
            }
        });
        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.addtask, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_add_task:
                Intent intent = new Intent(getActivity(), AddTaskActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
