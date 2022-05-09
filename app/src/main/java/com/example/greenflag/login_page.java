package com.example.greenflag;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.ImageButton;

import com.google.android.material.textfield.TextInputEditText;

public class login_page extends AppCompatActivity {
    ImageButton btn_back;
    ImageButton btn_create_account;
    TextInputEditText tiet_email;
    TextInputEditText tiet_create_password;
    TextInputEditText tiet_repeat_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        btn_back = findViewById(R.id.ibtn_back);
        btn_create_account = findViewById(R.id.btn_create_account);
        tiet_email = findViewById(R.id.tiet_email);
        tiet_create_password = findViewById(R.id.tiet_create_password);
        tiet_repeat_password = findViewById(R.id.tiet_repeat_password);

        tiet_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // validation code
                String emailInput = tiet_email.getText().toString().trim();

               if(emailInput.isEmpty()){
                    tiet_email.setError("Field can't be empty");
               }else{


               }

            }
        }
        );

    }


}