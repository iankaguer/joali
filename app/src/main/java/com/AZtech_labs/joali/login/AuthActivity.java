package com.AZtech_labs.joali.login;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.AZtech_labs.joali.R;


public class AuthActivity extends AppCompatActivity {
    ImageView iconLogin, iconRegister, iconForgot;
    LogInFragment logInFragment;
    ForgotFragment forgotFragment;
    SignUpFragment signUpFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        logInFragment = new LogInFragment();
        signUpFragment = new SignUpFragment();
        forgotFragment = new ForgotFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.setCustomAnimations(R.anim.fadeout, R.anim.fadein);
        transaction.replace(R.id.auth_frame, logInFragment);
        transaction.commit();

        iconLogin = findViewById(R.id.icon_login);
        iconRegister = findViewById(R.id.icon_register);
        iconForgot = findViewById(R.id.icon_forgot);
        iconLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(logInFragment);
            }
        });
        iconRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(signUpFragment);
            }
        });
        iconForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(forgotFragment);
            }
        });


    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction().setCustomAnimations(R.anim.fadeout, R.anim.fadein)
                    .replace(R.id.auth_frame, fragment)
                    .commit();
            return true;
        }
        return false;
    }

}


