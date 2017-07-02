package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        number.setText(intent.getStringExtra("number"));

    }
}
