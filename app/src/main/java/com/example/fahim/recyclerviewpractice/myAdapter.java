package com.example.fahim.recyclerviewpractice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.viewHolder> {

    private Context c;
    private List<Contact> contacts;

    public myAdapter(Context c, List<Contact> contacts) {
        this.c = c;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(c).inflate(R.layout.list_item, viewGroup, false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        viewHolder.nameView.setText(contacts.get(i).getName());
        viewHolder.nameView2.setText(contacts.get(i).getAddress());

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class viewHolder extends RecyclerView.ViewHolder{

        TextView nameView;
        TextView nameView2;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.textView);
            nameView2 = itemView.findViewById(R.id.textView2);
        }
    }
}
