package com.example.resedue.resedue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference ref;
    List<users> list;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("users").child("123");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<users>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                    Toast.makeText(MainActivity.this, dataSnapshot1.getKey(), Toast.LENGTH_SHORT).show();
                    users val = dataSnapshot1.getValue(users.class);
                    users user = new users();
                    String name = val.getName();
//                    Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                    String add = val.getAddress();
                    String status= val.getStatus();
                    String mob = val.getMob();
                    String sell = val.getSell();
                    String ref=val.getRef();
                    ref=dataSnapshot1.getKey();
                    String wt=val.getWt();

                    user.setName(name);
                    user.setAddress(add);
                    user.setStatus(status);
                    user.setMob(mob);
                    user.setSell(sell);
                    user.setRef(ref);
                    user.setWt(wt);
                    //user.setKey(dataSnapshot1.getKey());
                    list.add(user);
                }
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(MainActivity.this,list);
                RecyclerView.LayoutManager recyce = new GridLayoutManager(MainActivity.this,1);
                recyclerView.setLayoutManager(recyce);
                recyclerView.setItemAnimator( new DefaultItemAnimator());
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("Hello", "Failed to read value.", error.toException());
            }
        });

    }
}
