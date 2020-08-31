package com.retical.perusal;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.retical.perusal.R;

import java.util.ArrayList;

public class QuestionsRecyclerView extends AppCompatActivity {
    RecyclerView recyclerView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_recycler_view);






        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("sem").child("sub");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String filename=dataSnapshot.getKey();
                String url=dataSnapshot.getValue(String.class);
                ((QuestionsAdapter)recyclerView.getAdapter()).update(filename,url);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

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
        recyclerView=findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(QuestionsRecyclerView.this));
        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onClick(View view, int position ) {
                Intent intent = new Intent(QuestionsRecyclerView.this,MainActivity.class);
                QuestionsAdapter myAdapter=new QuestionsAdapter();
                //intent.putExtra("url",myAdapter.urls.get(position));
                intent.putExtra("position",position);
                startActivity(intent);
            }
        };
        QuestionsAdapter myAdapter=new QuestionsAdapter(recyclerView,QuestionsRecyclerView.this,new ArrayList<String>(),new ArrayList<String>(),itemClickListener);
        recyclerView.setAdapter(myAdapter);
    }
}
