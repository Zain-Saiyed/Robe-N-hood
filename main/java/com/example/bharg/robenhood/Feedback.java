package com.example.bharg.robenhood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.regex.Pattern;

public class Feedback extends AppCompatActivity {
    EditText t1,t2,t3,t4;
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
        setContentView(R.layout.activity_feedback);
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        t4=findViewById(R.id.t4);
        bt=findViewById(R.id.btsb);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((t1.getText().toString().isEmpty())||(t2.getText().toString().isEmpty())||(t3.getText().toString().isEmpty())||(t4.getText().toString().isEmpty())) {

                    Toast.makeText(Feedback.this,"Enter The Fields",Toast.LENGTH_LONG).show();

                }
                else if(!isValidEmail(t2.getText().toString())){
                    Toast.makeText(Feedback.this,"Invalid Mail ",Toast.LENGTH_LONG).show();
                }
                else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference().child("Feedback").child(str).child("Name");
                    String s = t1.getText().toString().trim();
                    myRef.setValue(s);
                    t1.setText("");

                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference().child("Feedback").child(str).child("email");
                    String s2 = t2.getText().toString().trim();
                    myRef.setValue(s2);
                    t2.setText("");


                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference().child("Feedback").child(str).child("Subject");
                    String s4 = t3.getText().toString().trim();
                    myRef.setValue(s4);
                    t3.setText("");

                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference().child("Feedback").child(str).child("Description");
                    String s5 = t4.getText().toString().trim();
                    myRef.setValue(s5);
                    t4.setText("");

                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference().child("Feedback").child(str).child("Token:");
                    myRef.setValue(str);
                    Toast.makeText(Feedback.this,"Feedback Submitted",Toast.LENGTH_LONG).show();

                    Intent intent= new Intent(com.example.bharg.robenhood.Feedback.this,com.example.bharg.robenhood.MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        });
    }
}
