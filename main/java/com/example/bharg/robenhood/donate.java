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

public class donate extends AppCompatActivity {

    EditText et_name,et_email,et_phone,et_address,et_quantity,et_desc;
    Button donate;
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
        setContentView(R.layout.activity_donate);
        et_address=findViewById(R.id.et_address);
        et_desc=findViewById(R.id.et_desc);
        et_email=findViewById(R.id.et_email);
        et_name=findViewById(R.id.et_name);
        et_phone=findViewById(R.id.et_phone);
        et_quantity=findViewById(R.id.et_quantity);
        donate=findViewById(R.id.donate);
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((et_name.getText().toString().isEmpty())||(et_email.getText().toString().isEmpty())||(et_phone.getText().toString().isEmpty())||(et_address.getText().toString().isEmpty())||(et_quantity.toString().isEmpty())) {

                    Toast.makeText(donate.this,"Enter The Fields",Toast.LENGTH_LONG).show();

                }
                else if(!isValidEmail(et_email.getText().toString())){
                    Toast.makeText(donate.this,"Invalid Mail ",Toast.LENGTH_LONG).show();
                }
                else if(!isValidMobile(et_phone.getText().toString())) {
                    Toast.makeText(donate.this,"Invalid Phone no.",Toast.LENGTH_LONG).show();
                }
                else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference().child("User Data").child(str).child("Name");
                    String s = et_name.getText().toString().trim();
                    myRef.setValue(s);
                    et_name.setText("");

                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference().child("User Data").child(str).child("Email");
                    String s2 = et_email.getText().toString().trim();
                    myRef.setValue(s2);
                    et_email.setText("");

                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference().child("User Data").child(str).child("Phone");
                    String s3 = et_phone.getText().toString().trim();
                    myRef.setValue(s3);
                    et_phone.setText("");

                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference().child("User Data").child(str).child("Address");
                    String s4 = et_address.getText().toString().trim();
                    myRef.setValue(s4);
                    et_address.setText("");

                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference().child("User Data").child(str).child("Quantity");
                    String s5 = et_quantity.getText().toString().trim();
                    myRef.setValue(s5);
                    et_quantity.setText("");

                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference().child("User Data").child(str).child("Decription");
                    String s6 = et_desc.getText().toString().trim();
                    myRef.setValue(s6);
                    et_desc.setText("");

                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference().child("User Data").child(str).child("Token:");
                    myRef.setValue(str);

                    Intent intent= new Intent(com.example.bharg.robenhood.donate.this,com.example.bharg.robenhood.donated.class);
                    startActivity(intent);
                    finish();

                }
            }
        });
    }
}
