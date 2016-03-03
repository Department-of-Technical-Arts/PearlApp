package com.tech.dota.pearl2k16;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.facebook.FacebookSdk;
import com.kogitune.activity_transition.ActivityTransition;
import com.squareup.picasso.Picasso;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Picasso.with(this).load(R.drawable.pearl_logo_black_comp)
                .fit()
                .into((ImageView) findViewById(R.id.imageLogo));

        ActivityTransition.with(getIntent()).to(findViewById(R.id.imageLogo)).duration(300).start(savedInstanceState);
        Button login;
        login=(Button) findViewById(R.id.login_button);

        AlphaAnimation animation1 = new AlphaAnimation(0.0f, 1.0f);
        animation1.setDuration(300);
        animation1.setStartOffset(300);
        animation1.setFillAfter(true);
        login.startAnimation(animation1);

    }
}
