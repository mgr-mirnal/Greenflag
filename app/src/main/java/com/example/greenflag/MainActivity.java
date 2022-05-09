package com.example.greenflag;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    Button btnCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    btnCreate = findViewById(R.id.btn_create_account);

    btnCreate.setOnClickListener(view ->{
        Intent intent = new Intent();
        intent.setClass(this, login_page.class);
        startActivity(intent);
    });
    }
}