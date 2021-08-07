package alqarara.municipality.alqararamunicipality.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import alqarara.municipality.alqararamunicipality.R;

public class SplashActivity extends AppCompatActivity {

     ImageView iv_logo;
      Animation downtoup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        iv_logo=findViewById(R.id.splash_iv_logo);
//        iv_logo.setVisibility(View.GONE);
        downtoup = AnimationUtils.loadAnimation(getBaseContext(), R.anim.downtoup);
        iv_logo.setVisibility(View.VISIBLE);
        iv_logo.setAnimation(downtoup);
        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(1000);
                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }

            }
        };
        background.start();
    }

}