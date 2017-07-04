package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by q on 2017-07-02.
 */

public class AppendContact extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.append_contact);

        Intent intent = getIntent();
        String name;
        String phoneNumber;

        try{
            name = intent.getStringExtra("name");
        }catch (NullPointerException e){
            name = "";
        }

        try {
            phoneNumber = intent.getStringExtra("number");
        }catch (NullPointerException e){
            phoneNumber = "";
        }

        final EditText editName = (EditText)findViewById(R.id.editName);
        final EditText editNumber = (EditText)findViewById(R.id.editNumber);
        final EditText editEmail = (EditText)findViewById(R.id.editEmail);
        Button btn = (Button)findViewById(R.id.btn);

        editName.setText(name);
        editNumber.setText(phoneNumber);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contactIntent = new Intent();

                contactIntent.putExtra("name", editName.getText().toString());
                contactIntent.putExtra("phoneNumber", editNumber.getText().toString());
                contactIntent.putExtra("email", editEmail.getText().toString());

                setResult(RESULT_OK, contactIntent);
                finish();
            }
        });
    }
}
