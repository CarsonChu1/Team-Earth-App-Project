package com.example.earthteamproject;

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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    String data1[], data2[];

    int images[];

    Context context;

    //this constructor recieves data from main ativity (title, description, image)
    public MyAdapter(Context ct, String s1[], String s2[], int img[]){

        context = ct;

        //title
        data1 = s1;

        //description
        data2 = s2;

        images = img;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.my_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        //set data

        //title
        holder.myText1.setText(data1[position]);

        //description
        holder.myText2.setText(data2[position]);

        //image
        holder.myImage.setImageResource(images[position]);

        //set onclick listener with my_row constaint layout object in view holder
        //this will allow the user to open a new activity upon tapping an item
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, SecondActivity.class);

                //setting data in secondactivity depending on item tapped
                intent.putExtra("data1", data1[position]);
                intent.putExtra("data2", data2[position]);

                intent.putExtra("images", images[position]);

                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {

        //return size of data (can use any of the arrays)
        return data1.length;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView myText1, myText2;

        ImageView myImage;

        //my_row constraint layout object
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            myText1 = itemView.findViewById(R.id.myText1);

            myText2 = itemView.findViewById(R.id.myText2);

            myImage = itemView.findViewById(R.id.imageView);

            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
