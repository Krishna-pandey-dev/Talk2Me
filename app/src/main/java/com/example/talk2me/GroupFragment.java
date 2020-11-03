package com.example.talk2me;


import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupFragment extends Fragment {
    private View groupfagmentview;
    private ListView list_view;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> lisy_of_groups =new ArrayList<>();
    private DatabaseReference Groupref;


    public GroupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        groupfagmentview = inflater.inflate(R.layout.fragment_group, container, false);
        Groupref = FirebaseDatabase.getInstance().getReference().child("Groups");

        initializeFields();


        RetriveandDisplayGroup();


        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                String currentGroupName = adapterView.getItemAtPosition(position).toString();
                Intent groupchatintent = new Intent(getContext(),GroupChatActivity.class);
                groupchatintent.putExtra("groupname",currentGroupName);
                startActivity(groupchatintent);
            }
        });

        return groupfagmentview;
    }

    private void RetriveandDisplayGroup()
    {

        Groupref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                Set<String> set = new HashSet<>();
                Iterator iterator = dataSnapshot.getChildren().iterator();

                while (iterator.hasNext())
                {
                    set.add(((DataSnapshot)iterator.next()).getKey());
                }
                lisy_of_groups.clear();
                lisy_of_groups.addAll(set);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initializeFields()
    {
        list_view = (ListView) groupfagmentview.findViewById(R.id.list_group);
        arrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,lisy_of_groups);
        list_view.setAdapter(arrayAdapter);


    }

}
