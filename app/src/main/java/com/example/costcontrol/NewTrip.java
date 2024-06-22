package com.example.costcontrol;

import android.annotation.SuppressLint;
import android.app.Activity;
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

import com.example.costcontrol.API.API;
import com.example.costcontrol.Models.Aereo;
import com.example.costcontrol.Models.EntretenimentoModel;
import com.example.costcontrol.Models.Gasolina;
import com.example.costcontrol.Models.Hospedagem;
import com.example.costcontrol.Models.Refeicao;
import com.example.costcontrol.Models.TripModel;
import com.example.costcontrol.Utils.ActivityFinder;
import com.example.costcontrol.Utils.DecimalFormatter;
import com.example.costcontrol.Utils.ExtraActivity;
import com.example.costcontrol.Utils.SweetAlert;
import com.example.costcontrol.persistance.SQLiteManager;
import com.example.costcontrol.persistance.models.Entreteinment;
import com.example.costcontrol.persistance.models.Trip;
import com.google.android.material.checkbox.MaterialCheckBox;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewTrip extends AppCompatActivity {

    EditText destinoInput, numeroViajantesInput, duracaoDiasInput, totalEstimadoQuilometrosInput,
            mediaQuilometrosLitroInput, custoMedioLitroInput, totalVeiculosInput, custoEstimadoPessoaInput,
            aluguelVeiculoInput, custoEstimadoRefeicaoInput, refeicoesDiaInput, custoMedioNoiteInput,
            totalNoitesInput, totalQuartosInput;
    TextView custoCombustivel, custoTarifaAerea, custoRefeicoes, custoHospedagem, custoEntretenimento, custoTotal;
    MaterialCheckBox combustivelCheckbox, tarifaAereaCheckbox, refeicoesCheckbox, hospedagemCheckbox;
    AppCompatButton adicionarBtn, salvarBtn;
    Integer userId;

    public double custoMedioLitroValue = 0, mediaQuilometrosLitroValue = 0,
            custoEstimadoPessoaValue = 0, aluguelVeiculoValue = 0, custoEstimadoRefeicaoValue = 0, custoMedioNoiteValue = 0,
            custoCombustivelValue = 0, custoTarifaAereaValue = 0, custoRefeicoesValue = 0,
            custoHospedagemValue = 0, custoTotalValue = 0, entretenimentoTemporario = 0;

    public Integer totalEstimadoQuilometrosValue = 0, numeroViajantesValue = 0, duracaoDiasValue = 0, totalVeiculosValue = 0, totalNoitesValue = 0, totalQuartosValue = 0, refeicoesDiaValue = 0;
    public List<EntretenimentoModel> entretenimentoValues = new ArrayList<>();
    Gasolina gasolina;
    Hospedagem hospedagem;
    Aereo aereo;
    Refeicao refeicao;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newtrip);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Colocando os listeners para os inputs
        inputSetup();
        configuracoesGeraisSetup();
        combustivelSetup();
        tarifaAereaSetup();
        refeicoesSetup();
        hospedagemSetup();
        entretenimentoSetup(this);

        Integer extraUserId = ExtraActivity.getUserId(this);

        //Erro caso o id do usuário não for encontrado. Volta para login.
        if (extraUserId == null) {
            ExtraActivity.start(this, () -> new Intent(this, LoginActivity.class));
        }

        userId = extraUserId;

        //Como não tem edição, é apenas necessário salvar a viagem.
        salvarBtn.setText(R.string.salvar);
        //Cria a viagem e tenta fazer o POST.
        salvarBtn.setOnClickListener(v -> saveOnClickListener(salvarBtn, extraUserId));
    }

    private void saveOnClickListener(View v, Integer extraUserId) {

        if (combustivelCheckbox.isChecked()) {
            gasolina = new Gasolina(
                    extraUserId,
                    totalEstimadoQuilometrosValue,
                    mediaQuilometrosLitroValue,
                    custoMedioLitroValue,
                    totalVeiculosValue
            );
        }

        if (hospedagemCheckbox.isChecked()) {
            hospedagem = new Hospedagem(
                    extraUserId,
                    custoMedioNoiteValue,
                    totalNoitesValue,
                    totalQuartosValue
            );
        }

        if (tarifaAereaCheckbox.isChecked()) {
            aereo = new Aereo(
                    extraUserId,
                    custoEstimadoPessoaValue,
                    aluguelVeiculoValue
            );
        }

        if (refeicoesCheckbox.isChecked()) {
            refeicao = new Refeicao(
                    extraUserId,
                    custoEstimadoRefeicaoValue,
                    refeicoesDiaValue
            );
        }

        TripModel newTrip = new TripModel(
                extraUserId,
                numeroViajantesValue,
                duracaoDiasValue,
                custoTotalValue,
                custoEstimadoPessoaValue,
                destinoInput.getText().toString(),
                gasolina,
                aereo,
                hospedagem,
                refeicao,
                entretenimentoValues
        );

        Activity currentActivity = ActivityFinder.getActivity(this);
        try {

            SweetAlertDialog alert = SweetAlert.showLoadingDialog(currentActivity);
            API.postTrips(newTrip, new Callback<TripModel>() {
                @Override
                public void onResponse(Call<TripModel> call, Response<TripModel> response) {
                    if (response != null && response.isSuccessful()) {
                        SweetAlert.closeAnyDialog(alert);
                        ExtraActivity.start(v.getContext(), () -> {
                            Intent intent = new Intent(v.getContext(), Trips.class);
                            return ExtraActivity.setUserId(intent, extraUserId);
                        });
                    }
                }

                @Override
                public void onFailure(Call<TripModel> call, Throwable t) {
                    SweetAlert.closeAnyDialog(alert);
                    SweetAlert.showErrorDialog(currentActivity, "Erro ao salvar viagem");
                }
            });
        } catch (Exception e) {
            SweetAlert.showErrorDialog(currentActivity, "Erro ao salvar viagem");
        }
    }

    public String doubleToString(Double value) {
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
                totalEstimadoQuilometrosValue = Integer.parseInt(s.toString());
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
                custoTotal.setText(doubleToString(custoTotalValue));
            } else {
                custoTotalValue -= custoCombustivelValue;
                custoTotal.setText(doubleToString(custoTotalValue));
            }
        });
    }

    public void updateCombustivel() {
        double result = 0;
        if (mediaQuilometrosLitroValue != 0 && totalVeiculosValue != 0) {
            result = ((totalEstimadoQuilometrosValue / mediaQuilometrosLitroValue) * custoMedioLitroValue) / totalVeiculosValue;
        }
        custoCombustivelValue = result;
        custoCombustivel.setText(doubleToString(result));
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
                custoTotal.setText(doubleToString(custoTotalValue));
            } else {
                custoTotalValue -= custoTarifaAereaValue;
                custoTotal.setText(doubleToString(custoTotalValue));
            }
        });
    }

    public void updateTarifaAerea() {
        double result = 0;
        result = (custoEstimadoPessoaValue * numeroViajantesValue) + aluguelVeiculoValue;
        custoTarifaAereaValue = result;
        custoTarifaAerea.setText(doubleToString(result));
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
                custoTotal.setText(doubleToString(custoTotalValue));
            } else {
                custoTotalValue -= custoRefeicoesValue;
                custoTotal.setText(doubleToString(custoTotalValue));
            }
        });
    }

    public void updateRefeicoes() {
        double result;
        result = ((refeicoesDiaValue * numeroViajantesValue) * custoEstimadoRefeicaoValue) * duracaoDiasValue;
        custoRefeicoesValue = result;
        custoRefeicoes.setText(doubleToString(result));
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
                custoTotal.setText(doubleToString(custoTotalValue));
            } else {
                custoTotalValue -= custoHospedagemValue;
                custoTotal.setText(doubleToString(custoTotalValue));
            }
        });
    }

    public void updateHospedagem() {
        double result;
        result = (custoMedioNoiteValue * totalNoitesValue) * totalQuartosValue;
        custoHospedagemValue = result;
        custoHospedagem.setText(doubleToString(result));
    }

    public void entretenimentoSetup(Context context) {
        adicionarBtn.setOnClickListener(v -> {
            createEntretenimentoElement(context, new EntretenimentoModel(userId, "", 0));
        });
    }

    public void createEntretenimentoElement(Context context, EntretenimentoModel entreteinment) {
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
                    entreteinment.entretenimento = "";
                    return;
                }
                entreteinment.entretenimento = s.toString();
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
                    entreteinment.valor = 0d;
                    return;
                }
                entreteinment.valor = Double.parseDouble(s.toString());
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

        if (entreteinment.entretenimento != null) {
            entretenimentoNome.setText(entreteinment.entretenimento);
        }
        if (entreteinment.valor != 0) {

            entretenimentoValor.setText(doubleToString(entreteinment.valor));
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
        double result = 0d;
        for (EntretenimentoModel current : entretenimentoValues) {
            result += current.valor;
        }
        if (result > entretenimentoTemporario) {
            custoTotalValue += (result - entretenimentoTemporario);
        } else {
            custoTotalValue -= (entretenimentoTemporario - result);
        }
        entretenimentoTemporario = result;
        custoEntretenimento.setText(doubleToString(result));
        custoTotal.setText(doubleToString(custoTotalValue));
    }

}