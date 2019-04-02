package com.sg.logincounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sg.logincounter.db.DatabaseHelper;
import com.sg.logincounter.model.User;

public class SignUpActivity extends AppCompatActivity {
    private Button signUpButton;
    private EditText email;
    private EditText name;
    private EditText password;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUpButton=findViewById(R.id.signupbutton2);
        email=findViewById(R.id.emails);
        name=findViewById(R.id.name);
        password=findViewById(R.id.passwords);
        databaseHelper=LoginActivity.databaseHelper;
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user=new User();
                user.setEmail(email.getText().toString().trim());
                user.setName(name.getText().toString().trim());
                user.setPassword(password.getText().toString().trim());
                if(email.getText().toString().trim().length()!=0&&name.getText().toString().trim().length()!=0&&password.getText().toString().trim().length()!=0)
                {
                    if(databaseHelper.checkUserEmail(user.getEmail())) {
                        Toast.makeText(SignUpActivity.this,"Email is already registered",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        databaseHelper.insertUser(user.getName(), user.getEmail(), user.getPassword());
                        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                    }
                }
                else
                    Toast.makeText(SignUpActivity.this,"Invalid Empty Input",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
