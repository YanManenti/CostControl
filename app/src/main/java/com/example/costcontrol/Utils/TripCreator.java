package com.example.costcontrol.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.costcontrol.API.API;
import com.example.costcontrol.Models.Aereo;
import com.example.costcontrol.Models.EntretenimentoModel;
import com.example.costcontrol.Models.Gasolina;
import com.example.costcontrol.Models.Hospedagem;
import com.example.costcontrol.Models.Refeicao;
import com.example.costcontrol.Models.TripModel;
import com.example.costcontrol.NewTrip;
import com.example.costcontrol.R;
import com.example.costcontrol.persistance.SQLiteManager;
import com.example.costcontrol.persistance.models.Entreteinment;
import com.example.costcontrol.persistance.models.Trip;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripCreator {

    @SuppressLint("SetTextI18n")
    public static void render(LinearLayout basicView,Resources resources, Context context, Integer userId) {
        basicView.removeAllViews();

        Activity currentActivity = ActivityFinder.getActivity(context);
        SweetAlertDialog alertDialog = SweetAlert.showLoadingDialog(currentActivity);

        API.getTripsByAccount(new Callback<ArrayList<TripModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TripModel>> call, Response<ArrayList<TripModel>> response) {
                if (response != null && response.isSuccessful()) {
                    //Pega apenas as viagens com id do usuário
                    ArrayList<TripModel> filteredResponse = response.body().stream().filter(trip -> trip.usuario == userId).collect(Collectors.toCollection(ArrayList::new));
                    asyncTripRender(basicView, resources, context, filteredResponse);
                }
                SweetAlert.closeAnyDialog(alertDialog);
            }
            @Override
            public void onFailure(Call<ArrayList<TripModel>> call, Throwable t) {
                SweetAlert.showErrorDialog(currentActivity,"Erro ao buscar viagens");
            }
        });
    }

    public static double calculoTotal(TripModel trip){
        double result = 0;
        if(trip.hospedagem!=null){
            Hospedagem hospedagem = trip.hospedagem;
            result += (hospedagem.custoMedioNoite*hospedagem.totalNoite)* hospedagem.totalQuartos;
        }
        if(trip.refeicao!=null){
            Refeicao refeicao = trip.refeicao;
            result += ((refeicao.refeicoesDia* trip.totalViajantes)* refeicao.custoRefeicao)*trip.duracaoViagem;
        }
        if(trip.aereo!=null){
            Aereo aereo = trip.aereo;
            result += (aereo.custoPessoa* trip.totalViajantes) + aereo.custoAluguelVeiculo;
        }

        //Check para divisão por zero
        if(trip.gasolina!=null && trip.gasolina.mediaKMLitro!=0 && trip.gasolina.totalVeiculos!=0){
            Gasolina gasolina = trip.gasolina;
            result += ((gasolina.totalEstimadoKM/gasolina.mediaKMLitro)*gasolina.custoMedioLitro)/gasolina.totalVeiculos;
        }
        if(trip.listaEntretenimento != null){
            for (EntretenimentoModel current: trip.listaEntretenimento) {
                result+=current.valor;
            }
        }
        return result;
    }

    public static double calculoPorPessoa(TripModel trip){
        if (trip.totalViajantes==0){
            return 0;
        }
        return calculoTotal(trip)/trip.totalViajantes;
    }

    @SuppressLint("DefaultLocale")
    public static void asyncTripRender(LinearLayout basicView, Resources resources, Context context, ArrayList<TripModel> list){
        int dp;
        for (TripModel trip :  list) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, 0, 15);
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setBackgroundResource(R.drawable.customyellowbutton);
            linearLayout.setBaselineAligned(false);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            dp = dpToPx.convert(resources, 15);
            linearLayout.setPadding(dp, dp, dp, dp);
            linearLayout.setWeightSum(2);

            TextView destinoText = new TextView(context);
            destinoText.setLayoutParams(layoutParams);
            destinoText.setText(trip.local);
            destinoText.setTextColor(resources.getColor(R.color.surfaceWhite, context.getTheme()));
            destinoText.setTextSize(30);
            destinoText.setTypeface(destinoText.getTypeface(), Typeface.BOLD);

            LinearLayout topWrapper = new LinearLayout(context);
            topWrapper.setLayoutParams(layoutParams);
            topWrapper.setBaselineAligned(false);
            topWrapper.setOrientation(LinearLayout.HORIZONTAL);
            topWrapper.setWeightSum(3);

            LinearLayout viajantesWrapper = new LinearLayout(context);
            LinearLayout.LayoutParams viajantesParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,1.7f);
            viajantesWrapper.setLayoutParams(viajantesParams);
            viajantesWrapper.setOrientation(LinearLayout.VERTICAL);

            TextView viajantesText = new TextView(context);
            viajantesText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            viajantesText.setText(resources.getText(R.string.viajantes));
            viajantesText.setTextSize(12);

            TextView viajantesValue = new TextView(context);
            viajantesValue.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            viajantesValue.setText(String.valueOf(trip.totalViajantes));
            viajantesValue.setTextColor(resources.getColor(R.color.surfaceOrange, context.getTheme()));
            viajantesValue.setTextSize(26);
            viajantesValue.setTypeface(viajantesValue.getTypeface(), Typeface.BOLD);

            LinearLayout precoPessoaWrapper = new LinearLayout(context);
            LinearLayout.LayoutParams precoPessoaParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,1.3f);
            precoPessoaWrapper.setLayoutParams(precoPessoaParams);
            precoPessoaWrapper.setOrientation(LinearLayout.VERTICAL);

            TextView precoPessoaText = new TextView(context);
            precoPessoaText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            precoPessoaText.setText(resources.getText(R.string.precoporpessoa));
            precoPessoaText.setTextSize(12);

            TextView precoPessoaValue = new TextView(context);
            precoPessoaValue.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            precoPessoaValue.setText(String.format("R$ %s", DecimalFormatter.format(calculoPorPessoa(trip))));
            precoPessoaValue.setTextColor(resources.getColor(R.color.surfaceOrange, context.getTheme()));
            precoPessoaValue.setTextSize(26);
            precoPessoaValue.setTypeface(precoPessoaValue.getTypeface(), Typeface.BOLD);

            LinearLayout bottomWrapper = new LinearLayout(context);
            bottomWrapper.setLayoutParams(layoutParams);
            bottomWrapper.setBaselineAligned(false);
            bottomWrapper.setOrientation(LinearLayout.HORIZONTAL);
            bottomWrapper.setWeightSum(3);

            LinearLayout duracaoWrapper = new LinearLayout(context);
            duracaoWrapper.setLayoutParams(viajantesParams);
            duracaoWrapper.setOrientation(LinearLayout.VERTICAL);

            TextView duracaoText = new TextView(context);
            duracaoText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            duracaoText.setText(R.string.duracao);
            duracaoText.setTextSize(12);

            TextView duracaoValue = new TextView(context);
            duracaoValue.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            duracaoValue.setText(String.format("%d dias", trip.duracaoViagem));
            duracaoValue.setTextColor(resources.getColor(R.color.surfaceOrange, context.getTheme()));
            duracaoValue.setTextSize(26);
            duracaoValue.setTypeface(duracaoValue.getTypeface(), Typeface.BOLD);

            LinearLayout precoTotalWrapper = new LinearLayout(context);
            precoTotalWrapper.setLayoutParams(precoPessoaParams);
            precoTotalWrapper.setOrientation(LinearLayout.VERTICAL);

            TextView precoTotalText = new TextView(context);
            precoTotalText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            precoTotalText.setText(R.string.precototal);
            precoTotalText.setTextSize(12);

            TextView precoTotalValue = new TextView(context);
            precoTotalValue.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            precoTotalValue.setText(String.format("R$ %s", DecimalFormatter.format(calculoTotal(trip))));

            precoTotalValue.setTextColor(resources.getColor(R.color.surfaceOrange, context.getTheme()));
            precoTotalValue.setTextSize(26);
            precoTotalValue.setTypeface(precoTotalValue.getTypeface(), Typeface.BOLD);





            basicView.addView(linearLayout);
            linearLayout.addView(destinoText);
            linearLayout.addView(topWrapper);
            topWrapper.addView(viajantesWrapper);
            viajantesWrapper.addView(viajantesText);
            viajantesWrapper.addView(viajantesValue);
            topWrapper.addView(precoPessoaWrapper);
            precoPessoaWrapper.addView(precoPessoaText);
            precoPessoaWrapper.addView(precoPessoaValue);
            linearLayout.addView(bottomWrapper);
            bottomWrapper.addView(duracaoWrapper);
            duracaoWrapper.addView(duracaoText);
            duracaoWrapper.addView(duracaoValue);
            bottomWrapper.addView(precoTotalWrapper);
            precoTotalWrapper.addView(precoTotalText);
            precoTotalWrapper.addView(precoTotalValue);

        }
    }

}
