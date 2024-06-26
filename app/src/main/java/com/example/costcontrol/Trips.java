package com.example.costcontrol;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.costcontrol.Models.TripModel;
import com.example.costcontrol.Utils.ExtraActivity;
import com.example.costcontrol.Utils.SweetAlert;
import com.example.costcontrol.Utils.TripCreator;
import com.example.costcontrol.persistance.SQLiteManager;
import com.example.costcontrol.persistance.models.Trip;

import java.util.ArrayList;
import java.util.List;

public class Trips extends AppCompatActivity {

    LinearLayout container;
    AppCompatButton criarViagemBtn;
    ImageButton updateTripsBtn;
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

        userId=ExtraActivity.getUserId(this);

        //Error handling
        if(userId==null){
            ExtraActivity.start(this, ()-> new Intent(this, LoginActivity.class));
        }

        container = findViewById(R.id.scrollContainer);
        TripCreator.render(container, getResources(),this, userId);


        updateTripsBtn = findViewById(R.id.atualizarViagemBtn);
        updateTripsBtn.setOnClickListener(v -> {
            TripCreator.render(container, getResources(),this, userId);
        });

        criarViagemBtn = findViewById(R.id.criarViagemBtn);
        criarViagemBtn.setOnClickListener(v -> {
            ExtraActivity.start(this, () -> {
                Intent intent = new Intent(this, NewTrip.class);
                return ExtraActivity.setUserId(intent, userId);
            });
        });
    }
}
