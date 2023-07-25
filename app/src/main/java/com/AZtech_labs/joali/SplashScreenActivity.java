package com.AZtech_labs.joali;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


import com.AZtech_labs.joali.login.AuthActivity;
import com.AZtech_labs.joali.services.BackgroundHome;
import com.airbnb.lottie.LottieAnimationView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import io.realm.Realm;

public class SplashScreenActivity extends AppCompatActivity {

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);
        BackgroundHome.grantPermissions(this);
        Realm.init(this);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("preferences", 0);

        try {
            BackgroundHome.run(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.animation);
        lottieAnimationView.setAnimation("introjson.json");
        lottieAnimationView.loop(false); lottieAnimationView.playAnimation();

        new Timer().schedule(new TimerTask(){
            public void run() {
                if (pref.contains("hasUser")){
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                }else{
                    startActivity(new Intent(SplashScreenActivity.this, AuthActivity.class));
                }

            }
        }, 3100);


        //finish();
    }
}
