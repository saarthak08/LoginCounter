package com.sg.logincounter;

import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sg.logincounter.db.DatabaseHelper;
import com.sg.logincounter.model.User;

public class LoginActivity extends AppCompatActivity {
    private Button signupButton;
    private Button loginButton;
    private EditText email;
    private EditText password;
    private TextView forgotpass;
    private User user;
    public static DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signupButton=findViewById(R.id.signupbutton);
        databaseHelper=new DatabaseHelper(LoginActivity.this);
        loginButton=findViewById(R.id.login_button);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().trim().length()!=0&&password.getText().toString().trim().length()!=0)
                {

                        user=new User();
                        user.setEmail(email.getText().toString().trim());
                        user.setPassword(password.getText().toString().trim());
                        if(databaseHelper.checkUserEmail(user.getEmail()))
                        {
                            if(databaseHelper.checkUserEmailPassword(user.getEmail(),user.getPassword()))
                            {
                                User user2=databaseHelper.getUser(email.getText().toString().trim());
                                user2.setCounter(user2.getCounter()+1);
                                databaseHelper.updateUser(user2);
                                Intent i=new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(i);
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this,"Invalid Password",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this,"No User Found",Toast.LENGTH_SHORT).show();
                        }
                }
                else {
                    Snackbar.make(v, "Email or Password is empty", Snackbar.LENGTH_SHORT).show();
                }

            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });
    }
}
