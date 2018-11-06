package com.example.think.eduhelper.Chat.ui.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.think.eduhelper.Chat.ui.Fragments.ChatFragment;
import com.example.think.eduhelper.R;

public class ChatActivity extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;

    public static void startActivity(Context context,
                                      String receiver,
                                      String receiverUid,
                                      String firebaseToken){
        Intent intent = new Intent(context,ChatActivity.class);
        intent.putExtra(com.example.think.eduhelper.Chat.utils.Constants.ARG_RECEIVER,receiver);
        intent.putExtra(com.example.think.eduhelper.Chat.utils.Constants.ARG_RECEIVER_UID,receiverUid);
        intent.putExtra(com.example.think.eduhelper.Chat.utils.Constants.ARG_FIREBASE_TOKEN,firebaseToken);
        context.startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        bindViews();
        init();
    }
    private void bindViews(){
        toolbar = findViewById(R.id.toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void init(){
        toolbar.setTitle(getIntent().getExtras().getString(com.example.think.eduhelper.Chat.utils.Constants.ARG_RECEIVER));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_content_chat,
                ChatFragment.newInstance(getIntent().getExtras().getString(com.example.think.eduhelper.Chat.utils.Constants.ARG_RECEIVER),
                        getIntent().getExtras().getString(com.example.think.eduhelper.Chat.utils.Constants.ARG_RECEIVER_UID),
                        getIntent().getExtras().getString(com.example.think.eduhelper.Chat.utils.Constants.ARG_FIREBASE_TOKEN)),
                ChatFragment.class.getSimpleName());
        fragmentTransaction.commit();

    }
}
