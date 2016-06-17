package com.tech.dota.pearl2016;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.ivbaranov.mfb.MaterialFavoriteButton;

import helper.CursorRecyclerAdapter;
import helper.EventTableManager;


public class EventListingFragment extends Fragment {


    public EventListingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_listing, container, false);
    }

    RecyclerView mRecyclerView;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView=(RecyclerView) view.findViewById(R.id.container);
        String tab=getArguments().getString("category");
        eventTableManager=new EventTableManager(getActivity());
        MyAdapter mAdapter=new MyAdapter(eventTableManager.getEvents(tab),getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    EventTableManager eventTableManager;

    class MyViewHolder extends RecyclerView.ViewHolder{

        View itemView;
        TextView name;
        CardView cardView;
        MaterialFavoriteButton fav;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
            cardView= (CardView) itemView.findViewById(R.id.event_card);
            name=(TextView) itemView.findViewById(R.id.event_card_name);
            fav= (MaterialFavoriteButton) itemView.findViewById(R.id.favourite_button);
        }
    }

    class  MyAdapter extends CursorRecyclerAdapter<MyViewHolder>{

        Context context;
        LayoutInflater inflater;
        CardView event_card;
        final int COLOR_1=0;
        final int COLOR_2=1;

        public MyAdapter(Cursor cursor, Context context) {
            super(cursor);
            this.context=context;
            inflater=LayoutInflater.from(context);

        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(inflater.inflate(R.layout.event_card,parent,false));
        }

        @Override
        public void onBindViewHolderCursor(final MyViewHolder holder, final Cursor cursor) {

            if(cursor.getPosition()%2==0){
                holder.cardView.setCardBackgroundColor(R.color.alternate_color1);
            }else {
                holder.cardView.setCardBackgroundColor(R.color.alternate_color2);
            }

            holder.name.setText(cursor.getString(cursor.getColumnIndex(EventTableManager.KEY_NAME)));
//            cursor.close();
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cursor.moveToPosition(holder.getAdapterPosition());
                    Intent intent=new Intent(getActivity(),EventDetailActivity.class);
                    intent.putExtra("event_id",cursor.getInt(cursor.getColumnIndex(EventTableManager.KEY_EVENT_ID)));
//                    ActivityTransitionLauncher.with(getActivity()).from(holder.itemView).launch(intent);
                    startActivity(intent);
                }
            });
            if(eventTableManager.checkFavourite(cursor.getInt(cursor.getColumnIndex(EventTableManager.KEY_EVENT_ID)))){
                holder.fav.setFavorite(true);
            }else{
                holder.fav.setFavorite(false);
            }
            holder.fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int toggle=0;
                    if(holder.fav.isFavorite()){
                        toggle=1;
                    }
                    cursor.moveToPosition(holder.getAdapterPosition());
                    switch (toggle) {
                        case 0:
                            eventTableManager.toggleFavourite(cursor.getInt(cursor.getColumnIndex(EventTableManager.KEY_EVENT_ID)));
                            holder.fav.setFavorite(true,true);
                            break;
                        case 1:
                            eventTableManager.toggleFavourite(cursor.getInt(cursor.getColumnIndex(EventTableManager.KEY_EVENT_ID)));
                            holder.fav.setFavorite(false,true);
                            break;
                    }
                }
            });
        }
    }
}