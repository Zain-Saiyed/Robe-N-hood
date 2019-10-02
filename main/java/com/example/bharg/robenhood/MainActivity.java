package com.example.bharg.robenhood;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
int i=0;
    Button call_btn,mail_btn;
    FloatingActionButton fab,fab2,fab3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        call_btn=findViewById(R.id.call_btn);
        mail_btn=findViewById(R.id.mail_btn);
        fab=findViewById(R.id.fab);
        fab2=findViewById(R.id.fab_2);
        fab3=findViewById(R.id.fab_3);
        call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:7575852011"));
                startActivity(intent);
            }
        });
        mail_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO,Uri.fromParts("mailto","teslanik99@gmail.com",null));
                startActivity(intent);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==0) {
                    fab2.setVisibility(View.VISIBLE);
                    fab3.setVisibility(View.VISIBLE);
                    i=1;
                }
                else{
                Intent intent=new Intent(MainActivity.this,com.example.bharg.robenhood.donate.class);
                startActivity(intent);
                i=0;
                fab2.setVisibility(View.INVISIBLE);
                fab3.setVisibility(View.INVISIBLE);
            }
        }
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,com.example.bharg.robenhood.Register.class);
                startActivity(intent);
                i=0;
                fab2.setVisibility(View.INVISIBLE);
                fab3.setVisibility(View.INVISIBLE);
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(com.example.bharg.robenhood.MainActivity.this,com.example.bharg.robenhood.Feedback.class);
                startActivity(intent);
                i=0;
                fab2.setVisibility(View.INVISIBLE);
                fab3.setVisibility(View.INVISIBLE);
            }
        });


    }


}
