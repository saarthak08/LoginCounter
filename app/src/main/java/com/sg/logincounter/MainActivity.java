package com.sg.logincounter;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.sg.logincounter.adapter.UsersAdapter;
import com.sg.logincounter.db.DatabaseHelper;
import com.sg.logincounter.model.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private ArrayList<User> users;
    private RecyclerView recyclerView;
    private UsersAdapter usersAdapter;
    public boolean doubleBackToExitPressedOnce=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Users List");
        databaseHelper=LoginActivity.databaseHelper;
        users=(ArrayList<User>)databaseHelper.getAllUsers();
        recyclerView=findViewById(R.id.rv);
        usersAdapter=new UsersAdapter(users);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(usersAdapter);
    }
    @Override
    public void onBackPressed() {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            doubleBackToExitPressedOnce = true;
            Toast.makeText(MainActivity.this,"Press \'BACK\' again to exit",Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            },2000);
        }
}
