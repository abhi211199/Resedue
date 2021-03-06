package com.example.resedue.resedue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    EditText t;
    TextView tx;
    List<users> list;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t=(EditText)findViewById(R.id.id);
        tx=(TextView)findViewById(R.id.tx);
    }
    public void fx(View view)
    {
        tx.setText("Searching...");
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("users").child(t.getText().toString());
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
                    String ref;
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
                    tx.setText("");
                }
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(MainActivity.this,list);
                RecyclerView.LayoutManager recyce = new GridLayoutManager(MainActivity.this,1);
                recyclerView.setLayoutManager(recyce);
                recyclerView.setItemAnimator( new DefaultItemAnimator());
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(MainActivity.this, "No Entries found!!", Toast.LENGTH_SHORT).show();
                Log.w("Hello", "Failed to read value.", error.toException());
            }
        });

    }
}
