package com.example.android.justjava;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

import static com.example.android.justjava.MainActivity.message;
import static com.example.android.justjava.MainActivity.name;
import static com.example.android.justjava.MainActivity.nameArray;
import static com.example.android.justjava.MainActivity.nameS;
import static com.example.android.justjava.MainActivity.prices;
import static com.example.android.justjava.MainActivity.summaryMessage;

/**
 * Created by uswer on 4/22/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyView> {

    static Integer[] drawableArray = {R.drawable.caffe_mocha1, R.drawable.caffe_americano, R.drawable.espresso1, R.drawable.cappucino1};
    protected ArrayList<DataModel> dataSet;
    int[] quantity = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    String quantityMsg[] = {"", "", "", "", "", "", "", "", "", ""};
    int totalPrice = 0;

    public RecyclerViewAdapter(ArrayList<DataModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_item, parent, false);
        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {
        TextView textViewName = holder.textView;
        ImageView imageView1 = holder.imageView;
        TextView textViewPrice = holder.price;
        TextView textViewAdd = holder.add;
        TextView textViewSubs = holder.subs;
        textViewName.setText(dataSet.get(position).getName());
        imageView1.setImageResource(dataSet.get(position).getImage());
        textViewPrice.setText(String.valueOf(dataSet.get(position).getId_()));

        textViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increment(position);
            }
        });

        textViewSubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrement(position);
            }

        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void increment(int position) {
        quantity[position]++;
        int price = prices[position] * quantity[position];
        totalPrice += prices[position];
        quantityMsg[position] = "\n" + String.valueOf(quantity[position] + " " + nameArray[position]) + " Rs. " + String.valueOf(price);
        displaySummaryMessage();

    }

    public void decrement(int position) {
        if (quantity[position] >= 2) {
            totalPrice -= prices[position];
            quantity[position]--;
            int price = prices[position] * quantity[position];
            quantityMsg[position] = "\n" + String.valueOf(quantity[position] + " " + nameArray[position]) + " Rs. " + String.valueOf(price);
        } else if (quantity[position] == 1) {
            quantity[position]--;
            totalPrice -= prices[position];
            quantityMsg[position] = "";
        }
        displaySummaryMessage();
    }

    private void displaySummaryMessage() {
        nameS = name.getText().toString();
        message = "Name: " + nameS;
        for (int i = 0; i < nameArray.length; i++) {
            message += quantityMsg[i];
        }
        message += "\nTotal Price: Rs. "  + String.valueOf(totalPrice);
        summaryMessage.setText(message);
    }

    public class MyView extends RecyclerView.ViewHolder {

        public TextView textView;
        public ImageView imageView;
        public TextView price;
        public TextView add;
        public TextView subs;


        public MyView(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.textview1);
            imageView = (ImageView) view.findViewById(R.id.imageView);
            price = (TextView) view.findViewById(R.id.price);
            add = (TextView) view.findViewById(R.id.textviewAdd);
            subs = (TextView) view.findViewById(R.id.textViewSubs);

        }
    }
}

