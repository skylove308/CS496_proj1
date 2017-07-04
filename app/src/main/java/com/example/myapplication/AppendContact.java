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

        final EditText editName = (EditText)findViewById(R.id.editName);
        final EditText editNumber = (EditText)findViewById(R.id.editNumber);
        final EditText editEmail = (EditText)findViewById(R.id.editEmail);
        Button btn = (Button)findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                intent.putExtra("name", editName.getText().toString());
                intent.putExtra("phoneNumber", editNumber.getText().toString());
                intent.putExtra("email", editEmail.getText().toString());

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
