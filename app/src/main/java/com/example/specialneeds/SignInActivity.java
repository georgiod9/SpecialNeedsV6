package com.example.specialneeds;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getSupportActionBar().hide();

        EditText username = (EditText) findViewById(R.id.editUsername);
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    hideKeyboard(v);
                }
            }
        });

        EditText password = (EditText) findViewById(R.id.editPasswordField);
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        ImageView logoView = (ImageView) findViewById(R.id.imageView_logo);

        TextView usernameView = (TextView) findViewById(R.id.usernamePrompt);
        TextView usernameInput = (TextView) findViewById(R.id.editUsername);

        TextView passwordView = (TextView) findViewById(R.id.TextViewPassword);
        TextView passwordInput = (TextView) findViewById(R.id.editPasswordField);

        Button loginButton = (Button) findViewById(R.id.button_login);

        logoView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.bottom_animation));
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {

                usernameView.setVisibility(View.VISIBLE);
                usernameView.startAnimation(AnimationUtils.loadAnimation(usernameView.getContext(),
                        R.anim.text_animation));

                usernameInput.setVisibility(View.VISIBLE);
                usernameInput.startAnimation(AnimationUtils.loadAnimation(usernameInput.getContext(),
                        R.anim.text_animation));

                passwordView.setVisibility(View.VISIBLE);
                passwordView.startAnimation(AnimationUtils.loadAnimation(passwordView.getContext(),
                        R.anim.text_animation));

                passwordInput.setVisibility(View.VISIBLE);
                passwordInput.startAnimation(AnimationUtils.loadAnimation(passwordInput.getContext(),
                        R.anim.text_animation));

                loginButton.setVisibility(View.VISIBLE);
                loginButton.startAnimation(AnimationUtils.loadAnimation(loginButton.getContext(),
                        R.anim.text_animation));
            }
        }, 1000);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}