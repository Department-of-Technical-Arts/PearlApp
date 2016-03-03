package com.tech.dota.pearl2k16;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.kogitune.activity_transition.ActivityTransition;
import com.kogitune.activity_transition.ActivityTransitionLauncher;
import com.squareup.picasso.Picasso;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Picasso .with(this).load(R.drawable.pearl_logo_black_comp)
                .fit()
                .into((ImageView)findViewById(R.id.logoContainer));
        findViewById(R.id.logoContainer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
//                ActivityTransition.with(intent).duration(200).start(savedInstanceState);
            }
        });
    }
}
