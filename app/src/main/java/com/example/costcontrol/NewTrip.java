package com.example.costcontrol;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.costcontrol.Models.EntretenimentoModel;
import com.example.costcontrol.persistance.SQLiteManager;
import com.example.costcontrol.persistance.models.Entreteinment;
import com.example.costcontrol.persistance.models.Trip;
import com.google.android.material.checkbox.MaterialCheckBox;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NewTrip extends AppCompatActivity {

    EditText destinoInput, numeroViajantesInput, duracaoDiasInput, totalEstimadoQuilometrosInput,
            mediaQuilometrosLitroInput, custoMedioLitroInput, totalVeiculosInput, custoEstimadoPessoaInput,
            aluguelVeiculoInput, custoEstimadoRefeicaoInput, refeicoesDiaInput, custoMedioNoiteInput,
            totalNoitesInput, totalQuartosInput;
    TextView custoCombustivel, custoTarifaAerea, custoRefeicoes, custoHospedagem, custoEntretenimento, custoTotal;
    MaterialCheckBox combustivelCheckbox, tarifaAereaCheckbox, refeicoesCheckbox, hospedagemCheckbox;
    AppCompatButton adicionarBtn, salvarBtn;
    Integer userId;

    public float totalEstimadoQuilometrosValue = 0, mediaQuilometrosLitroValue = 0, custoMedioLitroValue = 0,
            custoEstimadoPessoaValue = 0, aluguelVeiculoValue = 0, custoEstimadoRefeicaoValue = 0, custoMedioNoiteValue = 0,
            custoCombustivelValue = 0, custoTarifaAereaValue = 0, custoRefeicoesValue = 0,
            custoHospedagemValue = 0, custoTotalValue = 0, entretenimentoTemporario = 0;

    public Integer numeroViajantesValue = 0, duracaoDiasValue = 0, totalVeiculosValue = 0, totalNoitesValue = 0, totalQuartosValue = 0, refeicoesDiaValue = 0;
    public List<Entreteinment> entretenimentoValues = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newtrip);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);

        inputSetup();
        configuracoesGeraisSetup();
        combustivelSetup();
        tarifaAereaSetup();
        refeicoesSetup();
        hospedagemSetup();
        entretenimentoSetup(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int extraUserId = extras.getInt("userId");
            Integer extraTripId = extras.getInt("tripId");
            userId = extraUserId;
            if (extraTripId != 0) {
                Trip currentTrip = sqLiteManager.getTripById(extraTripId);
                entretenimentoValues = sqLiteManager.listEntreteinmentByTripId(extraTripId);
                updateHospedagem();
                updateRefeicoes();
                updateCombustivel();
                updateTarifaAerea();
                updateEntretenimento();

                destinoInput.setText(currentTrip.destino);
                numeroViajantesInput.setText(currentTrip.numeroViajantes.toString());
                numeroViajantesValue = currentTrip.numeroViajantes;
                duracaoDiasInput.setText(currentTrip.duracaoDias.toString());
                duracaoDiasValue = currentTrip.duracaoDias;
                totalEstimadoQuilometrosInput.setText(currentTrip.totalEstimadoQuilometros.toString());
                totalEstimadoQuilometrosValue = currentTrip.totalEstimadoQuilometros;
                mediaQuilometrosLitroInput.setText(currentTrip.mediaQuilometrosLitro.toString());
                mediaQuilometrosLitroValue = currentTrip.mediaQuilometrosLitro;
                custoMedioLitroInput.setText(currentTrip.custoMedioLitro.toString());
                custoMedioLitroValue = currentTrip.custoMedioLitro;
                totalVeiculosInput.setText(currentTrip.totalVeiculos.toString());
                totalVeiculosValue = currentTrip.totalVeiculos;
                custoEstimadoPessoaInput.setText(currentTrip.custoEstimadoPessoa.toString());
                custoEstimadoPessoaValue = currentTrip.custoEstimadoPessoa;
                aluguelVeiculoInput.setText(currentTrip.aluguelVeiculo.toString());
                aluguelVeiculoValue = currentTrip.aluguelVeiculo;
                custoEstimadoRefeicaoInput.setText(currentTrip.custoEstimadoRefeicao.toString());
                custoEstimadoRefeicaoValue = currentTrip.custoEstimadoRefeicao;
                refeicoesDiaInput.setText(currentTrip.refeicoesDia.toString());
                refeicoesDiaValue = currentTrip.refeicoesDia;
                custoMedioNoiteInput.setText(currentTrip.custoMedioNoite.toString());
                custoMedioNoiteValue = currentTrip.custoMedioNoite;
                totalNoitesInput.setText(currentTrip.totalNoites.toString());
                totalNoitesValue = currentTrip.totalNoites;
                totalQuartosInput.setText(currentTrip.totalQuartos.toString());
                totalQuartosValue = currentTrip.totalQuartos;
                combustivelCheckbox.setChecked(currentTrip.combustivel);
                tarifaAereaCheckbox.setChecked(currentTrip.tarifaAerea);
                refeicoesCheckbox.setChecked(currentTrip.refeicoes);
                hospedagemCheckbox.setChecked(currentTrip.refeicoes);
                for (Entreteinment entreteinment : entretenimentoValues) {
                    createEntretenimentoElement(this, entreteinment);
                }

                salvarBtn.setText(R.string.atualizar);
                salvarBtn.setOnClickListener(v -> {
                    Trip newTrip = new Trip(extraTripId, destinoInput.getText().toString(), numeroViajantesValue,
                            duracaoDiasValue, combustivelCheckbox.isChecked(), totalEstimadoQuilometrosValue,
                            mediaQuilometrosLitroValue, custoMedioLitroValue, totalVeiculosValue, custoMedioNoiteValue,
                            totalNoitesValue, totalQuartosValue, userId, tarifaAereaCheckbox.isChecked(),
                            custoEstimadoPessoaValue, aluguelVeiculoValue, refeicoesCheckbox.isChecked(),
                            custoEstimadoRefeicaoValue, refeicoesDiaValue, hospedagemCheckbox.isChecked());
                    sqLiteManager.updateTrip(newTrip);
                    List<Entreteinment> currentEntreteinment = sqLiteManager.listEntreteinmentByTripId(extraTripId);
                    for (Entreteinment entretenimento : currentEntreteinment) {
                        sqLiteManager.deleteEntreteinmentById(entretenimento.getId());
                    }
                    for (Entreteinment entretenimento : entretenimentoValues) {
                        entretenimento.setTripId(extraTripId);
                        sqLiteManager.addEntreteinmentToDatabase(entretenimento);
                    }
                    startActivity(new Intent(this, Trips.class));
                });
                return;
            }
        }
        salvarBtn.setText(R.string.salvar);
        salvarBtn.setOnClickListener(v -> {
            Trip newTrip = new Trip(null, destinoInput.getText().toString(), numeroViajantesValue,
                    duracaoDiasValue, combustivelCheckbox.isChecked(), totalEstimadoQuilometrosValue,
                    mediaQuilometrosLitroValue, custoMedioLitroValue, totalVeiculosValue, custoMedioNoiteValue,
                    totalNoitesValue, totalQuartosValue, userId, tarifaAereaCheckbox.isChecked(),
                    custoEstimadoPessoaValue, aluguelVeiculoValue, refeicoesCheckbox.isChecked(),
                    custoEstimadoRefeicaoValue, refeicoesDiaValue, hospedagemCheckbox.isChecked());
            Trip result = sqLiteManager.addTripToDatabase(newTrip);

            for (Entreteinment entretenimento : entretenimentoValues) {
                entretenimento.setTripId(result.getId());
                sqLiteManager.addEntreteinmentToDatabase(entretenimento);
            }
            Intent intent = new Intent(this, Trips.class);
            intent.putExtra("userId", userId);
            startActivity(intent);

        });

    }

    public String floatToString(Float value) {
        return String.format(Locale.CANADA_FRENCH, "%.2f", value);
    }

    public int dpToPx(float dp) {
        return Math.round(dp * getResources().getDisplayMetrics().density);
    }

    public void inputSetup() {
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
        adicionarBtn = findViewById(R.id.adicionarBtn);
        salvarBtn = findViewById(R.id.salvarBtn);
    }

    public void configuracoesGeraisSetup() {
        numeroViajantesInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
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
                if (s.length() == 0) {
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

    public void combustivelSetup() {
        totalEstimadoQuilometrosInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                combustivelCheckbox.setChecked(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
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
                combustivelCheckbox.setChecked(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
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
                combustivelCheckbox.setChecked(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
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
                combustivelCheckbox.setChecked(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
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
            if (materialCheckBox.isChecked()) {
                custoTotalValue += custoCombustivelValue;
                custoTotal.setText(floatToString(custoTotalValue));
            } else {
                custoTotalValue -= custoCombustivelValue;
                custoTotal.setText(floatToString(custoTotalValue));
            }
        });
    }

    public void updateCombustivel() {
        float result = 0;
        if (mediaQuilometrosLitroValue != 0 && totalVeiculosValue != 0) {
            result = ((totalEstimadoQuilometrosValue / mediaQuilometrosLitroValue) * custoMedioLitroValue) / totalVeiculosValue;
        }
        custoCombustivelValue = result;
        custoCombustivel.setText(floatToString(result));
    }

    public void tarifaAereaSetup() {
        custoEstimadoPessoaInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tarifaAereaCheckbox.setChecked(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
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
                tarifaAereaCheckbox.setChecked(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
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
            if (materialCheckBox.isChecked()) {
                custoTotalValue += custoTarifaAereaValue;
                custoTotal.setText(floatToString(custoTotalValue));
            } else {
                custoTotalValue -= custoTarifaAereaValue;
                custoTotal.setText(floatToString(custoTotalValue));
            }
        });
    }

    public void updateTarifaAerea() {
        float result = 0;
        result = (custoEstimadoPessoaValue * numeroViajantesValue) + aluguelVeiculoValue;
        custoTarifaAereaValue = result;
        custoTarifaAerea.setText(floatToString(result));
    }

    public void refeicoesSetup() {
        custoEstimadoRefeicaoInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                refeicoesCheckbox.setChecked(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
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
                refeicoesCheckbox.setChecked(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
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
            if (materialCheckBox.isChecked()) {
                custoTotalValue += custoRefeicoesValue;
                custoTotal.setText(floatToString(custoTotalValue));
            } else {
                custoTotalValue -= custoRefeicoesValue;
                custoTotal.setText(floatToString(custoTotalValue));
            }
        });
    }

    public void updateRefeicoes() {
        float result;
        result = ((refeicoesDiaValue * numeroViajantesValue) * custoEstimadoRefeicaoValue) * duracaoDiasValue;
        custoRefeicoesValue = result;
        custoRefeicoes.setText(floatToString(result));
    }

    public void hospedagemSetup() {
        custoMedioNoiteInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                hospedagemCheckbox.setChecked(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
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
                hospedagemCheckbox.setChecked(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
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
                hospedagemCheckbox.setChecked(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
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
            if (materialCheckBox.isChecked()) {
                custoTotalValue += custoHospedagemValue;
                custoTotal.setText(floatToString(custoTotalValue));
            } else {
                custoTotalValue -= custoHospedagemValue;
                custoTotal.setText(floatToString(custoTotalValue));
            }
        });
    }

    public void updateHospedagem() {
        float result;
        result = (custoMedioNoiteValue * totalNoitesValue) * totalQuartosValue;
        custoHospedagemValue = result;
        custoHospedagem.setText(floatToString(result));
    }

    public void entretenimentoSetup(Context context) {


        adicionarBtn.setOnClickListener(v -> {
            createEntretenimentoElement(context, new Entreteinment());
        });
    }

    public void createEntretenimentoElement(Context context, Entreteinment entreteinment) {
        LinearLayout entretenimentoLayout;
        EditText entretenimentoNome, entretenimentoValor;
        ImageButton deleteBtn;
        LinearLayout parent = findViewById(R.id.entretenimentoContainer);


        entretenimentoLayout = new LinearLayout(context);
        entretenimentoNome = new EditText(context);
        entretenimentoValor = new EditText(context);
        deleteBtn = new ImageButton(context);

        entretenimentoLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        entretenimentoLayout.setOrientation(LinearLayout.HORIZONTAL);
        entretenimentoLayout.setWeightSum(5);

        LinearLayout.LayoutParams nomeParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 3f);
        nomeParams.setMargins(dpToPx(5), dpToPx(5), dpToPx(5), dpToPx(5));
        entretenimentoNome.setLayoutParams(nomeParams);
        entretenimentoNome.setBackgroundResource(R.drawable.custominputbackground);
        entretenimentoNome.setHint(R.string.nomeEntretenimentoPlaceholder);
        entretenimentoNome.setInputType(InputType.TYPE_CLASS_TEXT);
        entretenimentoNome.setPadding(dpToPx(10), dpToPx(10), dpToPx(10), dpToPx(10));
        entretenimentoNome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    entreteinment.setName("");
                    return;
                }
                entreteinment.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        LinearLayout.LayoutParams valorParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.5f);
        valorParams.setMargins(dpToPx(0), dpToPx(5), dpToPx(0), dpToPx(5));
        entretenimentoValor.setLayoutParams(valorParams);
        entretenimentoValor.setBackgroundResource(R.drawable.custominputbackground);
        entretenimentoValor.setHint(R.string.precoEntretenimentoPlaceholder);
        entretenimentoValor.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        entretenimentoValor.setPadding(dpToPx(10), dpToPx(10), dpToPx(10), dpToPx(10));
        entretenimentoValor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    entreteinment.setPrice(0f);
                    return;
                }
                entreteinment.setPrice(Float.parseFloat(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateEntretenimento();
            }
        });

        LinearLayout.LayoutParams deleteParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.5f);
        deleteParams.setMargins(dpToPx(5), dpToPx(5), dpToPx(5), dpToPx(5));
        deleteBtn.setLayoutParams(deleteParams);
        deleteBtn.setBackgroundResource(R.drawable.customyellowbutton);
        deleteBtn.setScaleX(1.4f);
        deleteBtn.setScaleY(1.4f);
        deleteBtn.setImageResource(R.drawable.delete_24px);
        deleteBtn.setColorFilter(ContextCompat.getColor(context, R.color.surfaceOrange));

        deleteBtn.setOnClickListener(v -> {
            entretenimentoValues.remove(entreteinment);
            updateEntretenimento();
            parent.removeView(entretenimentoLayout);
        });

        if (entreteinment.getName() != null) {
            entretenimentoNome.setText(entreteinment.getName());
        }
        if (entreteinment.getPrice() != null) {

            entretenimentoValor.setText(entreteinment.getPrice().toString());
        }


        parent.addView(entretenimentoLayout);
        entretenimentoLayout.addView(entretenimentoNome);
        entretenimentoLayout.addView(entretenimentoValor);
        entretenimentoLayout.addView(deleteBtn);

        if (!entretenimentoValues.contains(entreteinment)) {
            entretenimentoValues.add(entreteinment);
        }

    }

    public void updateEntretenimento() {
        Float result = 0f;
        for (Entreteinment current : entretenimentoValues) {
            result += current.getPrice();
        }
        if (result > entretenimentoTemporario) {
            custoTotalValue += (result - entretenimentoTemporario);
        } else {
            custoTotalValue -= (entretenimentoTemporario - result);
        }
        entretenimentoTemporario = result;
        custoEntretenimento.setText(floatToString(result));
        custoTotal.setText(floatToString(custoTotalValue));
    }

//    public float calculoTotal(Boolean hospedagem, Boolean refeicoes, Boolean tarifaAerea, Boolean combustivel,
//                              float custoMedioNoiteValue, Integer totalNoitesValue, Integer totalQuartosValue,
//                              Integer refeicoesDiaValue, Integer numeroViajantesValue, float custoEstimadoRefeicaoValue,
//                              Integer duracaoDiasValue, float custoEstimadoPessoaValue, float aluguelVeiculoValue,
//                              float mediaQuilometrosLitroValue, Integer totalVeiculosValue, float totalEstimadoQuilometrosValue,
//                              float custoMedioLitroValue, List<Entreteinment> entretenimentoValues){
//        float result = 0;
//        if(hospedagem){
//            result += (custoMedioNoiteValue*totalNoitesValue)*totalQuartosValue;
//        }
//        if(refeicoes){
//            result += ((refeicoesDiaValue*numeroViajantesValue)*custoEstimadoRefeicaoValue)*duracaoDiasValue;
//        }
//        if(tarifaAerea){
//            result += (custoEstimadoPessoaValue*numeroViajantesValue) + aluguelVeiculoValue;
//        }
//
//        if(combustivel && mediaQuilometrosLitroValue!=0 && totalVeiculosValue!=0){
//            result = ((totalEstimadoQuilometrosValue/mediaQuilometrosLitroValue)*custoMedioLitroValue)/totalVeiculosValue;
//        }
//        for (Entreteinment current: entretenimentoValues) {
//            result+=current.getPrice();
//        }
//        return result;
//    }

}