package com.example.talk2me;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolBar;
    private ViewPager myViewPager;
    private TabLayout myTabLayout;
    private TabAccessorAdapter myTabAccesorAdapter;
    private FirebaseUser currentuser;
    private FirebaseAuth mAuth;
    private DatabaseReference RootRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();
        currentuser=mAuth.getCurrentUser();
        RootRef= FirebaseDatabase.getInstance().getReference();


        mToolBar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("Talk2Me");

        myViewPager=(ViewPager) findViewById(R.id.main_tab_pager);
        myTabAccesorAdapter = new TabAccessorAdapter(getSupportFragmentManager());
        myViewPager.setAdapter(myTabAccesorAdapter);


        myTabLayout = (TabLayout) findViewById(R.id.main_tab);
        myTabLayout.setupWithViewPager(myViewPager);


    }


    @Override
    protected void onStart()
    {
        super.onStart();
        if (currentuser == null)
        {
            SendUserToLogInActivity();
        }
        else
        {
            VerifyUserExistance();
        }
    }

    private void VerifyUserExistance()
    {
        String currentUserId = mAuth.getCurrentUser().getUid();
        RootRef.child("Users").child(currentUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if ((dataSnapshot.child("name").exists()))
                {
                    Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SendUserToSettingsActivity();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.main_logout_option)
        {
            mAuth.signOut();
            SendUserToLogInActivity();
        }
        if(item.getItemId()==R.id.main_setting_option)
        {
            SendUserToSettingsActivity();
        }
        if(item.getItemId()==R.id.main_create_group)
        {
            RequestNewGroup();
        }
        if(item.getItemId()==R.id.main_find_friends_option)
        {

        }
        return true;
    }

    private void RequestNewGroup()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this,R.style.AlertDialouge);
        builder.setTitle("Enter Group Name:");
        final EditText groupnamefield = new EditText(MainActivity.this);
        groupnamefield.setHint("Group Name");
        builder.setView(groupnamefield);


        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                String groupname = groupnamefield.getText().toString();

                if(TextUtils.isEmpty(groupname))
                {
                    Toast.makeText(MainActivity.this, "Please Write a Group Name", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    createnewgroup(groupname);
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void createnewgroup(final String groupname)
    {
        RootRef.child("Groups").child(groupname).setValue("").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
            if(task.isSuccessful())
            {
                Toast.makeText(MainActivity.this, groupname + " Created Successfully...", Toast.LENGTH_SHORT).show();
            }
            }
        });

    }


    private void SendUserToLogInActivity()
    {

        Intent LogInIntent = new Intent(MainActivity.this,LogInActivity.class);
        LogInIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(LogInIntent);
        finish();
    }
    private void SendUserToSettingsActivity()
    {

        Intent SettingsIntent = new Intent(MainActivity.this,SettingsActivity.class);
        SettingsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(SettingsIntent);
        finish();
    }
}
