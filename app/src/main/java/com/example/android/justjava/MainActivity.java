package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static TextView summaryMessage;
    public static EditText name;
    public static String nameS, message;
    protected static ArrayList<DataModel> data;
    static Integer[] drawableArray = {R.drawable.espresso, R.drawable.double_espresso, R.drawable.caffe_mocha1,
            R.drawable.caffe_americano, R.drawable.cappucino1, R.drawable.cafe_latte, R.drawable.flat_white,
            R.drawable.affogato, R.drawable.short_macchiato, R.drawable.long_macchiato};
    static String[] nameArray = {"Espresso", "Double Espresso", "Caffe Mocha", "Caffe Americano", "Cappucino",
            "Cafe Latte", "Flat White", "Affogato", "Short Macchiato", "Long Macchiato"};
    static Integer[] prices = {50, 90, 90, 80, 80, 60, 60, 100, 80, 100};
    RecyclerView recyclerView;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    RecyclerViewAdapter RecyclerViewHorizontalAdapter;
    LinearLayoutManager HorizontalLayout;
    View ChildView;
    int RecyclerViewItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button orderButton = (Button) findViewById(R.id.button);
        name = (EditText) findViewById(R.id.editTextName);
        summaryMessage = (TextView) findViewById(R.id.summaryMessage);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);
        RecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(RecyclerViewLayoutManager);

        // Adding items to RecyclerView.
        //AddItemsToRecyclerViewArrayList();
        data = new ArrayList<DataModel>();
        for (int i = 0; i < 10; i++) {
            data.add(new DataModel(nameArray[i], prices[i], drawableArray[i]));
        }

        RecyclerViewHorizontalAdapter = new RecyclerViewAdapter(data);
        HorizontalLayout = new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.setAdapter(RecyclerViewHorizontalAdapter);


        // Adding on item click listener to RecyclerView.
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(MainActivity.this,
                    new GestureDetector.SimpleOnGestureListener() {
                        @Override
                        public boolean onSingleTapUp(MotionEvent motionEvent) {
                            return true;
                        }
                    });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {
                ChildView = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if (ChildView != null && gestureDetector.onTouchEvent(motionEvent)) {

                    //Getting clicked value.
                    RecyclerViewItemPosition = Recyclerview.getChildAdapterPosition(ChildView);

                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message +=  "\nThankyou!";
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subjectEmail) + nameS);
                intent.putExtra(Intent.EXTRA_TEXT, message);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }

}