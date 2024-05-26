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

import com.google.android.material.checkbox.MaterialCheckBox;

import java.math.BigDecimal;
import java.util.Locale;

public class NewTrip extends AppCompatActivity {

    EditText destinoInput, numeroViajantesInput, duracaoDiasInput, totalEstimadoQuilometrosInput,
            mediaQuilometrosLitroInput, custoMedioLitroInput, totalVeiculosInput, custoEstimadoPessoaInput,
            aluguelVeiculoInput, custoEstimadoRefeicaoInput, refeicoesDiaInput, custoMedioNoiteInput,
            totalNoitesInput, totalQuartosInput;
    TextView custoCombustivel, custoTarifaAerea, custoRefeicoes, custoHospedagem, custoEntretenimento, custoTotal;
    MaterialCheckBox combustivelCheckbox, tarifaAereaCheckbox, refeicoesCheckbox, hospedagemCheckbox;

    public float numeroViajantesValue = 0, duracaoDiasValue = 0, totalEstimadoQuilometrosValue = 0,   mediaQuilometrosLitroValue = 0,  custoMedioLitroValue = 0,
            totalVeiculosValue = 0, custoEstimadoPessoaValue = 0,  aluguelVeiculoValue = 0, custoEstimadoRefeicaoValue = 0, refeicoesDiaValue = 0, custoMedioNoiteValue = 0,
            totalNoitesValue = 0, totalQuartosValue = 0, custoCombustivelValue = 0, custoTarifaAereaValue = 0, custoRefeicoesValue = 0, custoHospedagemValue = 0, custoTotalValue = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newtrip);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        inputSetup();
        configuracoesGeraisSetup();
        combustivelSetup();
        tarifaAereaSetup();
        refeicoesSetup();
        hospedagemSetup();
    }


    public void inputSetup(){
        destinoInput = findViewById(R.id.destinoInput);
        numeroViajantesInput = findViewById(R.id.numeroViajantesInput);
        duracaoDiasInput = findViewById(R.id.duracaoDiasInput);
        totalEstimadoQuilometrosInput = findViewById(R.id.totalEstimadoQuilometrosInput);
        mediaQuilometrosLitroInput = findViewById(R.id.mediaQuilometrosLitroInput);
        custoMedioLitroInput = findViewById(R.id.custoMedioLitroInput);
        totalVeiculosInput = findViewById(R.id.totalVeiculosInput);
        custoEstimadoPessoaInput = findViewById(R.id.custoEstimadoPessoaInput);
        aluguelVeiculoInput = findViewById(R.id.aluguelVeiculoInput);
        custoEstimadoRefeicaoInput = findViewById(R.id.custoEstimadoRefeicaoInput);
        refeicoesDiaInput = findViewById(R.id.refeicoesDiaInput);
        custoMedioNoiteInput = findViewById(R.id.custoMedioNoiteInput);
        totalNoitesInput = findViewById(R.id.totalNoitesInput);
        totalQuartosInput = findViewById(R.id.totalQuartosInput);
        custoCombustivel = findViewById(R.id.custoCombustivel);
        custoTarifaAerea = findViewById(R.id.custoTarifaAerea);
        custoRefeicoes = findViewById(R.id.custoRefeicoes);
        custoHospedagem = findViewById(R.id.custoHospedagem);
        custoEntretenimento = findViewById(R.id.custoEntretenimento);
        combustivelCheckbox = findViewById(R.id.combustivelCheckbox);
        tarifaAereaCheckbox = findViewById(R.id.tarifaAereaCheckbox);
        refeicoesCheckbox = findViewById(R.id.refeicoesCheckbox);
        hospedagemCheckbox = findViewById(R.id.hospedagemCheckbox);
        custoTotal = findViewById(R.id.custoTotal);
    }

    public void configuracoesGeraisSetup(){
        numeroViajantesInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    numeroViajantesValue = 0;
                    return;
                }
                numeroViajantesValue = Integer.parseInt(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateTarifaAerea();
            }
        });

        duracaoDiasInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    duracaoDiasValue = 0;
                    return;
                }
                duracaoDiasValue = Integer.parseInt(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void combustivelSetup(){
        totalEstimadoQuilometrosInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    totalEstimadoQuilometrosValue = 0;
                    return;
                }
                totalEstimadoQuilometrosValue = Float.parseFloat(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateCombustivel();
            }
        });

        mediaQuilometrosLitroInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    mediaQuilometrosLitroValue = 0;
                    return;
                }
                mediaQuilometrosLitroValue = Float.parseFloat(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateCombustivel();
            }
        });

        custoMedioLitroInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    custoMedioLitroValue = 0;
                    return;
                }
                custoMedioLitroValue = Float.parseFloat(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateCombustivel();
            }
        });

        totalVeiculosInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    totalVeiculosValue = 0;
                    return;
                }
                totalVeiculosValue = Integer.parseInt(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateCombustivel();
            }
        });

        combustivelCheckbox.addOnCheckedStateChangedListener((materialCheckBox, i) -> {
            if(materialCheckBox.isChecked()){
                custoTotalValue += custoCombustivelValue;
                custoTotal.setText(floatToString(custoTotalValue));
            }else{
                custoTotalValue-=custoCombustivelValue;
                custoTotal.setText(floatToString(custoTotalValue));
            }
        });
    }

    public void updateCombustivel(){
        float result = 0;
        if(mediaQuilometrosLitroValue!=0 && totalVeiculosValue!=0){
            result = ((totalEstimadoQuilometrosValue/mediaQuilometrosLitroValue)*custoMedioLitroValue)/totalVeiculosValue;
        }
        custoCombustivelValue = result;
        custoCombustivel.setText(floatToString(result));
    }



    public String floatToString(Float value){
        return String.format(Locale.CANADA_FRENCH, "%.2f", value);
    }

    public void tarifaAereaSetup(){
        custoEstimadoPessoaInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    custoEstimadoPessoaValue = 0;
                    return;
                }
                custoEstimadoPessoaValue = Float.parseFloat(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateTarifaAerea();
            }
        });

        aluguelVeiculoInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    aluguelVeiculoValue = 0;
                    return;
                }
                aluguelVeiculoValue = Float.parseFloat(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateTarifaAerea();
            }
        });

        tarifaAereaCheckbox.addOnCheckedStateChangedListener((materialCheckBox, i) -> {
            if(materialCheckBox.isChecked()){
                custoTotalValue += custoTarifaAereaValue;
                custoTotal.setText(floatToString(custoTotalValue));
            }else{
                custoTotalValue-=custoTarifaAereaValue;
                custoTotal.setText(floatToString(custoTotalValue));
            }
        });
    }

    public void updateTarifaAerea(){
        float result = 0;
        result = (custoEstimadoPessoaValue*numeroViajantesValue) + aluguelVeiculoValue;
        custoTarifaAereaValue = result;
        custoTarifaAerea.setText(floatToString(result));
    }

    public void refeicoesSetup(){
        custoEstimadoRefeicaoInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    custoEstimadoRefeicaoValue = 0;
                    return;
                }
                custoEstimadoRefeicaoValue = Float.parseFloat(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateRefeicoes();
            }
        });

        refeicoesDiaInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    refeicoesDiaValue = 0;
                    return;
                }
                refeicoesDiaValue = Integer.parseInt(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateRefeicoes();
            }
        });

        refeicoesCheckbox.addOnCheckedStateChangedListener((materialCheckBox, i) -> {
            if(materialCheckBox.isChecked()){
                custoTotalValue += custoRefeicoesValue;
                custoTotal.setText(floatToString(custoTotalValue));
            }else{
                custoTotalValue-=custoRefeicoesValue;
                custoTotal.setText(floatToString(custoTotalValue));
            }
        });
    }

    public void updateRefeicoes(){
        float result;
        result = ((refeicoesDiaValue*numeroViajantesValue)*custoEstimadoRefeicaoValue)*duracaoDiasValue;
        custoRefeicoesValue = result;
        custoRefeicoes.setText(floatToString(result));
    }

    public void hospedagemSetup(){
        custoMedioNoiteInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    custoMedioNoiteValue = 0;
                    return;
                }
                custoMedioNoiteValue = Float.parseFloat(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateHospedagem();
            }
        });

        totalNoitesInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    totalNoitesValue = 0;
                    return;
                }
                totalNoitesValue = Integer.parseInt(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateHospedagem();
            }
        });

        totalQuartosInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    totalQuartosValue = 0;
                    return;
                }
                totalQuartosValue = Integer.parseInt(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateHospedagem();
            }
        });

        hospedagemCheckbox.addOnCheckedStateChangedListener((materialCheckBox, i) -> {
            if(materialCheckBox.isChecked()){
                custoTotalValue += custoHospedagemValue;
                custoTotal.setText(floatToString(custoTotalValue));
            }else{
                custoTotalValue-=custoHospedagemValue;
                custoTotal.setText(floatToString(custoTotalValue));
            }
        });
    };

    public void updateHospedagem(){
        float result;
        result = (custoMedioNoiteValue*totalNoitesValue)*totalQuartosValue;
        custoHospedagemValue = result;
        custoHospedagem.setText(floatToString(result));
    };
}