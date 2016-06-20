package com.tech.dota.pearl2016;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class GuideActivity extends AppCompatActivity {

    ImageButton reach,contact,campus,about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        about= (ImageButton) findViewById(R.id.about_us);
        reach= (ImageButton) findViewById(R.id.reach_campus);
        contact= (ImageButton) findViewById(R.id.contact_us);
        campus= (ImageButton) findViewById(R.id.campus_map);
        reach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GuideActivity.this,UniversalContainerActivity.class);
                intent.putExtra("frag",0);
                startActivity(intent);
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GuideActivity.this,UniversalContainerActivity.class);
                intent.putExtra("frag",1);
                startActivity(intent);
            }
        });
        campus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GuideActivity.this,UniversalContainerActivity.class);
                intent.putExtra("frag",2);
                startActivity(intent);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GuideActivity.this,UniversalContainerActivity.class);
                intent.putExtra("frag",5);
                startActivity(intent);
            }
        });
//        credits.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(GuideActivity.this,UniversalContainerActivity.class);
//                intent.putExtra("frag",6);
//                startActivity(intent);
//            }
//        });
    }
}
