package com.example.bharg.robenhood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Random;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    EditText t1,t2,t3;
    Button bt;

    private boolean isValidEmail(CharSequence email) {
        if (!TextUtils.isEmpty(email)) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
        return false;
    }

    private boolean isValidMobile(String phone) {
        boolean check=false;
        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            if(phone.length() != 10) {
                check = false;
            } else {
                check = true;
            }
        } else {
            check=false;
        }
        return check;
    }

    String str=FirebaseInstanceId.getInstance().getToken();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        t1=findViewById(R.id.rgt1);
        t2=findViewById(R.id.rgt2);
        t3=findViewById(R.id.rgt3);
        bt=findViewById(R.id.btsb);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((t1.getText().toString().isEmpty())||(t2.getText().toString().isEmpty())||(t3.getText().toString().isEmpty())) {

                    Toast.makeText(Register.this,"Enter The Fields",Toast.LENGTH_LONG).show();

                }
                else if(!isValidEmail(t2.getText().toString())){
                    Toast.makeText(Register.this,"Invalid Mail no.",Toast.LENGTH_LONG).show();
                }
                else if(!isValidMobile(t3.getText().toString())) {
                    Toast.makeText(Register.this,"Invalid Phone no.",Toast.LENGTH_LONG).show();
                }
                else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference().child("NGO").child(str).child("Name");
                    String s = t1.getText().toString().trim();
                    myRef.setValue(s);
                    t1.setText("");

                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference().child("NGO").child(str).child("Mail");
                    String s2 = t2.getText().toString().trim();
                    myRef.setValue(s2);
                    t2.setText("");


                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference().child("NGO").child(str).child("Phone");
                    String s4 = t3.getText().toString().trim();
                    myRef.setValue(s4);
                    t3.setText("");
                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference().child("NGO").child(str).child("Token:");
                    myRef.setValue(str);

                    Toast.makeText(Register.this,"Registration Successful",Toast.LENGTH_LONG).show();

                    Intent intent= new Intent(com.example.bharg.robenhood.Register.this,com.example.bharg.robenhood.MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        });
    }
}
