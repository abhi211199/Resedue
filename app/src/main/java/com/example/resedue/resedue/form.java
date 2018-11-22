package com.example.resedue.resedue;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

public class form extends AppCompatActivity {

    String[] country = {"Please Enter choice of selling agent", "Government", "Private"};
    EditText name,add,wt,sell,mob,id;
    FirebaseDatabase database;
    users user;
    String s,key;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        name=(EditText)findViewById(R.id.name);
        add=(EditText)findViewById(R.id.address);
        wt=(EditText)findViewById(R.id.wt);
        mob=(EditText)findViewById(R.id.mob);
        id=(EditText)findViewById(R.id.id);
        Spinner spin = (Spinner) findViewById(R.id.sell);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        database=FirebaseDatabase.getInstance();
        user=new users();
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s=country[position];
//                Toast.makeText(getApplicationContext(),s.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //getting values of form
    public void getVal()
    {
        user.setAddress(name.getText().toString());
        user.setMob(mob.getText().toString());
        user.setName(name.getText().toString());
        user.setWt(wt.getText().toString());
        user.setSell(s.toString());
        user.setStatus("PENDING");
    }

    //adding values in database
    public void add(View view)
    {
        final Date currentTime = Calendar.getInstance().getTime();
        getVal();
        database=FirebaseDatabase.getInstance();
        ref=database.getReference("users");
        key = ref.push().getKey();
//        Toast.makeText(this,key,Toast.LENGTH_LONG).show();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getVal();
                ref.child("123").child(key).setValue(user);
                Toast.makeText(form.this,"success".toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
