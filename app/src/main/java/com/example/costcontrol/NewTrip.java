package com.example.costcontrol;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.math.BigDecimal;

public class NewTrip extends AppCompatActivity {

    EditText totalEstimadoQuilometrosInput, mediaQuilometrosLitroInput;
    TextView custoCombustivel;

    public BigDecimal totalEstimadoQuilometrosValue = BigDecimal.valueOf(0), mediaQuilometrosLitroValue = BigDecimal.valueOf(0), custoCombustivelValue = BigDecimal.valueOf(0);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newtrip);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        totalEstimadoQuilometrosInput = findViewById(R.id.totalEstimadoQuilometrosInput);
        mediaQuilometrosLitroInput = findViewById(R.id.mediaQuilometrosLitroInput);
        custoCombustivel = findViewById(R.id.custoCombustivel);

        totalEstimadoQuilometrosInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                totalEstimadoQuilometrosValue = new BigDecimal(String.valueOf(s));
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateCusto();
            }
        });

        mediaQuilometrosLitroInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mediaQuilometrosLitroValue = new BigDecimal(String.valueOf(s));
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateCusto();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void updateCusto(){
        BigDecimal result;
        result = mediaQuilometrosLitroValue.add(totalEstimadoQuilometrosValue);
        custoCombustivel.setText(result.toString());
    }
}