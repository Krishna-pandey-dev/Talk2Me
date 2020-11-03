package com.example.talk2me;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

public class GroupChatActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ImageButton sendMessageButton;
    private EditText usermessageinput;
    private ScrollView mscrollview;
    private TextView displayTextmessage;

    private FirebaseAuth mAuth;
    private String currentGroupName,currentUserID,currentuserName,currentDate,currentTime;
    private DatabaseReference UsersRef , GroupNameRef, GroupMessageKeyref;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);


        currentGroupName = getIntent().getExtras().get("groupname").toString();
        Toast.makeText(GroupChatActivity.this, currentGroupName, Toast.LENGTH_SHORT).show();


        mAuth = FirebaseAuth.getInstance();
        currentUserID=mAuth.getCurrentUser().getUid();
        UsersRef= FirebaseDatabase.getInstance().getReference().child("Users");
        GroupNameRef = FirebaseDatabase.getInstance().getReference().child("Groups").child(currentGroupName);




        intializeFields();
        Getuserinnfo();

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Savemessagetodatabase();

                usermessageinput.setText("");
                mscrollview.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();
        GroupNameRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists())
                {
                    displaymessages(dataSnapshot);
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                if(dataSnapshot.exists())
                {
                    displaymessages(dataSnapshot);
                }

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    private void intializeFields() {


        mToolbar = (Toolbar) findViewById(R.id.groupchat_bar_layout);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(currentGroupName);
        sendMessageButton = (ImageButton) findViewById(R.id.send_message_button);
        usermessageinput = (EditText)findViewById(R.id.input_groupMessage);
        mscrollview = (ScrollView) findViewById(R.id.my_scroll_view);
        displayTextmessage = (TextView) findViewById(R.id.groupchat_text_display);

    }


    private void Getuserinnfo()
    {
        UsersRef.child(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {
                    currentuserName = dataSnapshot.child("name").getValue().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void Savemessagetodatabase()
    {
        String message = usermessageinput.getText().toString();
        String messageKEY = GroupNameRef.push().getKey();
        if (TextUtils.isEmpty(message))
        {
            Toast.makeText(this, "Please Enter a message...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Calendar calFordate = Calendar.getInstance();
            SimpleDateFormat CurrentDateFormat = new SimpleDateFormat("MMM ,dd, yyyy");
            currentDate = CurrentDateFormat.format(calFordate.getTime());

            Calendar calForTime = Calendar.getInstance();
            SimpleDateFormat CurrentTimeFormat = new SimpleDateFormat("hh:mm:ss a");
            currentTime = CurrentTimeFormat.format(calForTime.getTime());


            HashMap<String,Object> groupMessagekey = new HashMap<>();
            GroupNameRef.updateChildren(groupMessagekey);
            GroupMessageKeyref = GroupNameRef.child(messageKEY);

            HashMap<String , Object>  messageInfoMap = new HashMap<>();
            messageInfoMap.put("name" , currentuserName);
            messageInfoMap.put("message" , message);
            messageInfoMap.put("date" , currentDate);
            messageInfoMap.put("time" , currentTime);
            GroupMessageKeyref.updateChildren(messageInfoMap);


        }
    }
    private void displaymessages(DataSnapshot dataSnapshot)
    {
        Iterator iterator = dataSnapshot.getChildren().iterator();
        while (iterator.hasNext())
        {
            String chatDate = (String) ((DataSnapshot)iterator.next()).getValue();
            String chatMessage = (String) ((DataSnapshot)iterator.next()).getValue();
            String chatName = (String) ((DataSnapshot)iterator.next()).getValue();
            String chatTime = (String) ((DataSnapshot)iterator.next()).getValue();

            displayTextmessage.append(chatName + ": " + chatMessage + "\n" + chatTime +"\n \n \n");
            mscrollview.fullScroll(ScrollView.FOCUS_DOWN);
        }
    }
}
