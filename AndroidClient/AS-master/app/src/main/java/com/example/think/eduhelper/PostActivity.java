package com.example.think.eduhelper;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.think.eduhelper.PostDB.PostDatabase;
import com.example.think.eduhelper.PostDB.PostInfo;

import java.util.List;

public class PostActivity extends AppCompatActivity {
    Toolbar toolbar;
    private EditText course, topic, detail;
    private Button bt_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        toolbar = findViewById(R.id.default_toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        course = findViewById(R.id.post_course);
        topic = findViewById(R.id.post_subtitle);
        detail = findViewById(R.id.post_detail);
        bt_confirm = findViewById(R.id.post_confirm);
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String post_course = course.getText().toString();
                String post_title = topic.getText().toString();
                String post_detail = detail.getText().toString();
                PostInfo postInfo = new PostInfo();
                if(!(post_course.equals("")||post_title.equals("")||post_detail.equals(""))){
                    postInfo.setCourse(post_course);
                    postInfo.setDetail(post_detail);
                    postInfo.setTitle(post_title);
                    MainActivity.postDatabase.postDao().addPost(postInfo);
                    //MainActivity.postDatabase.postDao().addPost(postInfo);
                    Toast.makeText(getApplicationContext(),"Post submitted successfully",Toast.LENGTH_SHORT).show();
                    List<PostInfo> posts= MainActivity.postDatabase.postDao().getPost();
                    String info = "";
                    for(PostInfo post :posts){
                        int id = post.getId();
                        String name = post.getCourse();
                        String email = post.getTitle();
                        String detailText = post.getDetail();
                        info = info + "\n\n"+"ID: "+id+"\n"+"Course: "+name+"\n"+"Title: "+email+"\n"+"Detail: "+detailText;
                    }
                    course.setText("");
                    topic.setText("");
                    detail.setText(info);
                }else{
                    Toast.makeText(getApplicationContext(),"Please fill up the information",Toast.LENGTH_SHORT).show();

                }
            }
        });


        }
}
