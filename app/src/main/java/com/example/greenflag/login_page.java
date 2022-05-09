package com.example.greenflag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class login_page extends AppCompatActivity {
    ImageButton btn_back;
    AppCompatButton btn_create;
    EditText tiet_email;
    ImageView iv_email_tick;
    EditText tiet_create_password;
    ImageView iv_password_tick;
    EditText tiet_repeat_password;
    ImageView iv_repeat_tick;


    TextView tv_Alerts;

    private boolean validEmail = false;
    private boolean validPassword = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        btn_back = findViewById(R.id.ibtn_back);
        btn_create = findViewById(R.id.btn_create);
        tiet_email = findViewById(R.id.tiet_email);
        iv_email_tick = findViewById(R.id.iv_tick_email);
        tiet_create_password = findViewById(R.id.tiet_create_password);
        iv_password_tick = findViewById(R.id.iv_tick_repeat);
        tiet_repeat_password = findViewById(R.id.tiet_repeat_password);
        iv_repeat_tick = findViewById(R.id.iv_tick_repeat);

        tv_Alerts = findViewById(R.id.tv_alerts);

        tiet_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String email = tiet_email.getText().toString();
                if(!email.isEmpty()){
                    EmailAddressChecker();
                }
                else{
                    CleanEditText(tiet_email, iv_email_tick);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    tiet_create_password.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String password = tiet_create_password.getText().toString();
            if(!password.isEmpty()){
                passwordsChecker();
            }else {
                validPassword = false;
                ModifyAlert("", View.INVISIBLE);
                CleanEditText(tiet_create_password, iv_password_tick);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    });
    tiet_repeat_password.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String repeatPassword = tiet_repeat_password.getText().toString();
            if(!repeatPassword.isEmpty()){
                passwordsChecker();
            }else{
                validPassword = false;
                ModifyAlert("",View.INVISIBLE);
                CleanEditText(tiet_repeat_password,iv_repeat_tick);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    });
    btn_create.setOnClickListener( view ->{
        create();
    });
    btn_back.setOnClickListener(view ->{
        Intent intent = new Intent();
        intent.setClass(this,MainActivity.class);
        startActivity(intent);
    });
    }
    private void EmailAddressChecker() {
            boolean patternMatches = Patterns.EMAIL_ADDRESS.matcher(tiet_email.getText()).matches();
            SetBackgroundAndTick(tiet_email,iv_email_tick,patternMatches);
            validEmail = patternMatches;
    }
    private void passwordsChecker(){
        String password = tiet_create_password.getText().toString();
        String RepPassword = tiet_repeat_password.getText().toString();
        boolean passwordValid = false;
        boolean repPassword = false;

        if(!password.isEmpty()){
            passwordValid = isValidPassword(password);
            SetBackgroundAndTick(tiet_create_password, iv_password_tick, passwordValid);
            if (passwordValid){
                validPassword = true;
                ModifyAlert("",View.INVISIBLE);
            }else{
                validPassword = false;
                ModifyAlert("One of the passwords you entered wasnt valid",View.VISIBLE);
            }
        }else{
            validPassword = false;
        }

        if(!RepPassword.isEmpty()){
            repPassword = isValidPassword(RepPassword);
            SetBackgroundAndTick(tiet_repeat_password, iv_repeat_tick, repPassword);
            if (repPassword){
                validPassword = true;
                ModifyAlert("",View.INVISIBLE);
            }else{
                validPassword = false;
                ModifyAlert("One of the passwords you entered wasnt valid",View.VISIBLE);
            }
        }else{
            validPassword = false;
        }

        if(!password.isEmpty() && !RepPassword.isEmpty() && passwordValid && repPassword){
            if(password.equals(RepPassword)){
                validPassword = true;
                ModifyAlert("",View.INVISIBLE);
                SetBackgroundAndTick(tiet_create_password, iv_password_tick, true);
                SetBackgroundAndTick(tiet_repeat_password, iv_repeat_tick, true);
            }else{
                ModifyAlert("Your passwords dont match", View.VISIBLE);
                SetBackgroundAndTick(tiet_create_password, iv_password_tick, false);
                SetBackgroundAndTick(tiet_repeat_password, iv_repeat_tick, false);
                validPassword = false;
            }
        }
    }



    private void CleanEditText(EditText et, ImageView iv) {
            et.setBackground(getDrawable(R.drawable.white_background));
            iv.setVisibility(View.INVISIBLE);
    }


    private void SetBackgroundAndTick(EditText et, ImageView iv, boolean isCorrect){
        if(isCorrect){
            et.setBackground(getDrawable(R.drawable.white_background));
            iv.setVisibility(View.VISIBLE);

        }else{
            iv.setVisibility(View.VISIBLE);
        }
    }

    private boolean isValidPassword(String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

   private void ModifyAlert(String alert, int vis){
        tv_Alerts.setText(alert);
        tv_Alerts.setVisibility(vis);
   }

    private void create(){
        if(validEmail && validPassword){
            Toast.makeText(this, "All its okay", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Not all its Okay", Toast.LENGTH_SHORT).show();
        }
    }


}