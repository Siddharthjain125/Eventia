package com.a7476.eventia.eventia;

import android.support.v7.widget.RecyclerView;

/**
 * Created by HP on 3/26/2018.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MyViewHolder> {

    private List<Event> eventsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,category,venue,city;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.item_title);
            category = (TextView) view.findViewById(R.id.item_Categ);
            venue = (TextView) view.findViewById(R.id.item_Venue);
            city = (TextView) view.findViewById(R.id.item_City);
        }
    }


    public EventsAdapter(List<Event> moviesList) {
        this.eventsList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Event event = eventsList.get(position);
        holder.title.setText(event.getName());
        holder.category.setText(event.getCategory());
        holder.venue.setText(event.getVenue());
        holder.city.setText(event.getCity());
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }
}
