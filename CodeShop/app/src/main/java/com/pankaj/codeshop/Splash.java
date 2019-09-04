package com.pankaj.codeshop;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    TextView txt;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        txt = (TextView) findViewById(R.id.textSplash);
        img = (ImageView) findViewById(R.id.splashImage);
        final Animation anim = AnimationUtils.loadAnimation(getBaseContext(), R.anim.alpha);

        img.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                txt.setText("Agamya Guitar Class");
                new Handler().postDelayed(new Runnable() {
                    // Using handler with postDelayed called runnable run method
                    @Override
                    public void run() {
                        Intent i = new Intent(Splash.this, Home.class);
                        startActivity(i);
                        // close this activity
                        finish();
                    }
                }, 2 * 1000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

    }
}
