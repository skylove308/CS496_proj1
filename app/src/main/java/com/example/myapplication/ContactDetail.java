package com.example.myapplication;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by q on 2017-07-02.
 */


public class ContactDetail extends AppCompatActivity {
    final int REQUEST_EDIT_CONTACT = 0;
    TextView name;
    TextView number;
    int position;
    String retName;
    String retNumber;
    int change_flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_detail);

        name = (TextView)findViewById(R.id.textView1);
        number = (TextView)findViewById(R.id.textView2);
        ImageView picture = (ImageView)findViewById(R.id.imageView);
        ImageView phone = (ImageView)findViewById(R.id.button1);
        ImageView modify = (ImageView)findViewById(R.id.button2);


        final Intent intent = getIntent();
        retName = intent.getStringExtra("name");
        retNumber = intent.getStringExtra("number");
        position = intent.getIntExtra("position", 0);

        name.setText(retName);
        number.setText(retNumber);
        picture.setImageResource(intent.getIntExtra("picture",0));

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent = getIntent();
                String phoneNumber = intent.getStringExtra("number");
                String tel = "tel:" + phoneNumber;
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(tel));
                startActivity(myIntent);
            }
        });

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent editContactIntent = new Intent(
                        getApplicationContext(),
                        AppendContact.class
                );
                editContactIntent.putExtra("name", retName);
                editContactIntent.putExtra("number", retNumber);
                startActivityForResult(editContactIntent, REQUEST_EDIT_CONTACT);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_EDIT_CONTACT && resultCode == RESULT_OK){
            change_flag = 1;
            retName = data.getStringExtra("name");
            retNumber = data.getStringExtra("number");
            String email = data.getStringExtra("email");

            name.setText(retName);
            number.setText(retNumber);

            name.invalidate();
            number.invalidate();
        }
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("name", retName);
        returnIntent.putExtra("number", retNumber);
        returnIntent.putExtra("position", position);
        returnIntent.putExtra("change_flag", change_flag);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}