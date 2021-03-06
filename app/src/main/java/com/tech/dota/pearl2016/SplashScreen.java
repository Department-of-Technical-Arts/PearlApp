package com.tech.dota.pearl2016;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Picasso.with(this).load(R.drawable.pearl_logo_black_comp)
                .fit()
                .into((ImageView) findViewById(R.id.logoContainer));
//        GifClass gifClass= (GifClass) findViewById(R.id.logoContainer);
//        if (gifClass!=null) {
//            gifClass.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    final Intent intent = new Intent(SplashScreen.this, MainActivity.class);
//                    startActivity(intent);
//                    overridePendingTransition(0, 0);
////                ActivityTransition.with(intent).duration(200).start(savedInstanceState);
//                }
//            });
//        }

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);

                finish();
                // close this activity
            }
        },1000);

    }


}
