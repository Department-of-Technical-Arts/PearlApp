package com.tech.dota.pearl2k16;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.tech.dota.pearl2k16.anim.FeedItemAnimator;

import xyz.hanks.library.SmallBang;
import xyz.hanks.library.SmallBangListener;

public class MainActivity extends AppCompatActivity{

    SmallBang smallBang;
//    ImageButton appcredits,timeline,mainevents,guidebook;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        guidebook= (ImageButton) findViewById(R.id.guide_book);
//        mainevents= (ImageButton) findViewById(R.id.main_events);
//        timeline= (ImageButton) findViewById(R.id.event_timeline);
//        appcredits= (ImageButton) findViewById(R.id.app_credits);

        mRecyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        MyAdapter adapter=new MyAdapter(this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL,false));
        smallBang=SmallBang.attach2Window(this);



//        guidebook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"GUIDE BOOK",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//        mainevents.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,Headliners.class));
//                Toast.makeText(getApplicationContext(), "MAIN EVENTS", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        timeline.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,Events.class));
//                Toast.makeText(getApplicationContext(), "TIMELINE", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        appcredits.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "APP CREDITS", Toast.LENGTH_SHORT).show();
//            }
//        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smallBang.bang(view, 80, new SmallBangListener() {
                    @Override
                    public void onAnimationStart() {

                    }

                    @Override
                    public void onAnimationEnd() {

                    }
                });
                Snackbar.make(view, "Long press to open Favourite Events", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(),"LONG CLICKED",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageButton imageButton;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageButton=(ImageButton) itemView.findViewById(R.id.main_events);
        }
    }
    LayoutInflater inflater;
    class  MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        Context context;
        int[] resources;

        FeedItemAnimator feedItemAnimator;

        public MyAdapter(Context context) {
            this.context = context;
            inflater=LayoutInflater.from(context);
            resources= new int[]{R.drawable.mainevents, R.drawable.eventstimeline, R.drawable.guidebook, R.drawable.appcredits};
            feedItemAnimator=new FeedItemAnimator();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(inflater.inflate(R.layout.custom_main_row,parent,false));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            feedItemAnimator.animateAdd(holder);
            holder.imageButton.setImageResource(resources[position]);
        }

        @Override
        public int getItemCount() {
            return resources.length;
        }
    }

}