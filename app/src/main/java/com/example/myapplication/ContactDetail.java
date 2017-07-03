package com.example.myapplication;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by q on 2017-07-02.
 */


public class ContactDetail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_detail);

        TextView name = (TextView)findViewById(R.id.textView1);
        TextView number = (TextView)findViewById(R.id.textView2);
        ImageView picture = (ImageView)findViewById(R.id.imageView);
        ImageView phone = (ImageView)findViewById(R.id.button1);
        ImageView modify = (ImageView)findViewById(R.id.button2);


        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        number.setText(intent.getStringExtra("number"));
        picture.setImageResource(intent.getIntExtra("picture",0));



    phone.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
        Intent intent = getIntent();
        String phonenumber = intent.getStringExtra("number");
        String tel = "tel:" + phonenumber;
        Intent myintent = new Intent(Intent.ACTION_VIEW, Uri.parse(tel));
        startActivity(myintent);
    }
    });
}
}