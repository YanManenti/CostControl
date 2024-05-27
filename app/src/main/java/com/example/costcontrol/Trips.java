package com.example.costcontrol;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.costcontrol.Models.TripModel;
import com.example.costcontrol.Utils.TripCreator;
import com.example.costcontrol.persistance.SQLiteManager;
import com.example.costcontrol.persistance.models.Trip;

import java.util.ArrayList;
import java.util.List;

public class Trips extends AppCompatActivity {

    LinearLayout container;
    AppCompatButton criarViagemBtn;
    Integer userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trips);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);

        container = findViewById(R.id.scrollContainer);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Integer value = extras.getInt("userId");
            if(value==-1){
                startActivity(new Intent(this, LoginActivity.class));
            }
            userId=value;
            List<Trip> userTrips = sqLiteManager.listTripsByUserId(value);
            new TripCreator(container, this, userTrips, value);
        }else{
            startActivity(new Intent(this, LoginActivity.class));
        }

        criarViagemBtn = findViewById(R.id.criarViagemBtn);
        criarViagemBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, NewTrip.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        });

        //Pegar as viagems do banco




//        TripCreator trips = new TripCreator(container, this, test);
    }
}
