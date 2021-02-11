package com.app.myapplication;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.MyViewHolder>{
    private Context context;
    private List<Ticket> contactList;
    private TicketsAdapterListener listener;

    @NonNull
    @Override
    public TicketAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ticket_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketAdapter.MyViewHolder holder, int position) {
        final Ticket ticket = contactList.get(position);

//        Glide.with(context)
//                .load(ticket.getAirline().getLogo())
//                .apply(RequestOptions.circleCropTransform())
//                .into(holder.logo);

        holder.airlineName.setText(ticket.getAirline().getName());

        holder.departure.setText(ticket.getDeparture() + " Dep");
        holder.arrival.setText(ticket.getArrival() + " Dest");

        holder.duration.setText(ticket.getFlightNumber());
        holder.duration.append(", " + ticket.getDuration());
        holder.stops.setText(ticket.getNumberOfStops() + " Stops");

        if (!TextUtils.isEmpty(ticket.getInstructions())) {
            holder.duration.append(", " + ticket.getInstructions());
        }

        if (ticket.getPrice() != null) {
            holder.price.setText("₹" + String.format("%.0f", ticket.getPrice().getPrice()));
            holder.seats.setText(ticket.getPrice().getSeats() + " Seats");
//            holder.loader.setVisibility(View.INVISIBLE);
        } else {
//            holder.loader.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public TicketAdapter(Context context, List<Ticket> contactList, TicketsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.contactList = contactList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView airlineName;

        ImageView logo;

        TextView stops;

        TextView seats;

        TextView departure;

        TextView arrival;


        TextView duration;


        TextView price;


//        SpinKitView loader;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
    public interface TicketsAdapterListener {
        void onTicketSelected(Ticket contact);
    }
}
