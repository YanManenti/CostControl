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

import java.util.ArrayList;
import java.util.List;

public class Trips extends AppCompatActivity {

    LinearLayout container;
    AppCompatButton criarViagemBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trips);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        criarViagemBtn = findViewById(R.id.criarViagemBtn);
        criarViagemBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, NewTrip.class));
        });

        container = findViewById(R.id.scrollContainer);
        List<TripModel> test = new ArrayList<TripModel>();
        TripModel trip = new TripModel();
        trip.id = "teste";
        trip.destino = "Teste";
        trip.numeroViajantes = 5;
        trip.duracaoDias = 5;
        trip.combustivel = false;
        trip.totalEstimadoQuilometros = 55.5F;
        trip.mediaQuilometrosLitro = 5.5F;
        trip.custoMedioLitro = 5;
        trip.totalVeiculos = 5;
        trip.tarifaAerea = false;
        trip.custoEstimadoPessoa = 55;
        trip.aluguelVeiculo = 1;
        trip.refeicoes = false;
        trip.custoEstimadoRefeicao = 5.5F;
        trip.refeicoesDia = 5;
        trip.hospedagem = false;
        trip.custoMedioNoite = 55;
        trip.totalNoites = 5;
        trip.totalQuartos = 5;
        trip.entretenimento = new ArrayList<>();
        test.add(trip);

        TripCreator trips = new TripCreator(container, this, test);
    }
}
