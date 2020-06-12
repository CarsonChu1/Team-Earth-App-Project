package com.example.produceapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    ProduceInfo[] produces;//Array of produces that store values from the JSON file
    private Context mContext;


    public RecyclerViewAdapter(ProduceInfo[] produces, Context context) {
        this.produces = produces;
        mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_view, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        ProduceInfo produce = produces[position];
        holder.imageView.setImageResource(produce.getImage());
        holder.nameView.setText(produce.getName());
        holder.descView.setText(produce.getDescShort());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, InfoActivity.class);
                MainActivity.lastProduce = produces[position];
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return produces.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView nameView;
        TextView descView;
        ConstraintLayout constraintLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            nameView = itemView.findViewById(R.id.nameMain);
            descView = itemView.findViewById(R.id.descMain);
            constraintLayout = itemView.findViewById(R.id.linear_layout);
        }
    }
}