package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by q on 2017-07-02.
 */

public class AppendContact extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.append_contact);

        EditText name = (EditText)findViewById(R.id.name);
        EditText number = (EditText)findViewById(R.id.number);
        EditText e_mail = (EditText)findViewById(R.id.e_mail);
        Button btn = (Button)findViewById(R.id.btn);
    }
}
