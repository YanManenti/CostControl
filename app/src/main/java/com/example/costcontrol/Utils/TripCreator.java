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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.costcontrol.Models.TripModel;
import com.example.costcontrol.NewTrip;
import com.example.costcontrol.R;
import com.example.costcontrol.persistance.SQLiteManager;
import com.example.costcontrol.persistance.models.Entreteinment;
import com.example.costcontrol.persistance.models.Trip;

import java.util.List;

public class TripCreator {
    Resources resources;

    public static int dpToPx( Resources resources,float dp) {
        return Math.round(dp * resources.getDisplayMetrics().density);
    }

    @SuppressLint("SetTextI18n")
    public static void render(LinearLayout basicView,Resources resources, Context context, Integer userId) {

        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(context);
        List<Trip> list = sqLiteManager.listTripsByUserId(userId);
//        resources = basicView.getResources();
        int dp;
        for (Trip trip :  list) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, 0, 5);
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setBackgroundResource(R.drawable.customyellowbutton);
            linearLayout.setBaselineAligned(false);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            dp = dpToPx(resources, 15);
            linearLayout.setPadding(dp, dp, dp, dp);
            linearLayout.setWeightSum(2);

            TextView destinoText = new TextView(context);
            destinoText.setLayoutParams(layoutParams);
            destinoText.setText(trip.destino);
            destinoText.setTextColor(resources.getColor(R.color.surfaceWhite, context.getTheme()));
            destinoText.setTextSize(30);
            destinoText.setTypeface(destinoText.getTypeface(), Typeface.BOLD);

            LinearLayout topWrapper = new LinearLayout(context);
            topWrapper.setLayoutParams(layoutParams);
            topWrapper.setBaselineAligned(false);
            topWrapper.setOrientation(LinearLayout.HORIZONTAL);
            topWrapper.setWeightSum(5);

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
            viajantesValue.setText(trip.numeroViajantes.toString());
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
            precoPessoaValue.setText("R$ "+trip.getCustoEstimadoPessoa());
            precoPessoaValue.setTextColor(resources.getColor(R.color.surfaceOrange, context.getTheme()));
            precoPessoaValue.setTextSize(26);
            precoPessoaValue.setTypeface(precoPessoaValue.getTypeface(), Typeface.BOLD);

            ImageButton configBtn = new ImageButton(context);
            LinearLayout.LayoutParams configBtnLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT, 2f);
            configBtnLayout.setMargins(5, 5, 5, 5);
            configBtn.setLayoutParams(configBtnLayout);
            configBtn.setScaleX(2);
            configBtn.setScaleY(2);
            configBtn.setImageResource(R.drawable.settings_24px);
            configBtn.setBackground(Drawable.createFromPath("drawable/customyellowbutton.xml"));
            configBtn.setColorFilter(ContextCompat.getColor(context,R.color.surfaceOrange));
            configBtn.setContentDescription(resources.getText(R.string.editarviajem));
            configBtn.setOnClickListener(v -> {
//                Intent intent = new Intent(v.getContext(), NewTrip.class);
//                intent.putExtra("tripId", trip.id);
//                intent.putExtra("userId", userId);
//                v.getContext().startActivity(intent);
                ExtraActivity.start(v.getContext(), () -> {
                    Intent intent = new Intent(v.getContext(), NewTrip.class);
                    ExtraActivity.setUserId(intent, userId);
                    return ExtraActivity.setTripId(intent, trip.id);
                });
            });

            LinearLayout bottomWrapper = new LinearLayout(context);
            bottomWrapper.setLayoutParams(layoutParams);
            bottomWrapper.setBaselineAligned(false);
            bottomWrapper.setOrientation(LinearLayout.HORIZONTAL);
            bottomWrapper.setWeightSum(5);

            LinearLayout duracaoWrapper = new LinearLayout(context);
            duracaoWrapper.setLayoutParams(viajantesParams);
            duracaoWrapper.setOrientation(LinearLayout.VERTICAL);

            TextView duracaoText = new TextView(context);
            duracaoText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            duracaoText.setText(R.string.duracao);
            duracaoText.setTextSize(12);

            TextView duracaoValue = new TextView(context);
            duracaoValue.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            duracaoValue.setText(trip.duracaoDias + " dias");
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
            List<Entreteinment> current = sqLiteManager.listEntreteinmentByTripId(trip.id);
            precoTotalValue.setText("R$ "+ calculoTotal(trip.hospedagem, trip.refeicoes, trip.tarifaAerea, trip.combustivel, trip.custoMedioNoite,
                    trip.totalNoites, trip.totalQuartos, trip.refeicoesDia, trip.numeroViajantes, trip.custoEstimadoRefeicao, trip.duracaoDias,
                    trip.custoEstimadoPessoa, trip.aluguelVeiculo, trip.mediaQuilometrosLitro, trip.totalVeiculos, trip.totalEstimadoQuilometros,
                    trip.custoMedioLitro,current));
            precoTotalValue.setTextColor(resources.getColor(R.color.surfaceOrange, context.getTheme()));
            precoTotalValue.setTextSize(26);
            precoTotalValue.setTypeface(precoTotalValue.getTypeface(), Typeface.BOLD);

            ImageButton deleteBtn = new ImageButton(context);
            deleteBtn.setLayoutParams(configBtnLayout);
            deleteBtn.setScaleY(2);
            deleteBtn.setScaleX(2);
            deleteBtn.setImageResource(R.drawable.delete_24px);
            deleteBtn.setBackground(Drawable.createFromPath("drawable/customyellowbutton.xml"));
            deleteBtn.setColorFilter(ContextCompat.getColor(context,R.color.surfaceOrange));
            deleteBtn.setContentDescription(resources.getText(R.string.excluirviajem));
            deleteBtn.setOnClickListener(v -> {
                sqLiteManager.deleteTripById(trip.id);
                basicView.removeAllViews();
                TripCreator.render(basicView,resources, context, userId);
            });




            basicView.addView(linearLayout);
            linearLayout.addView(destinoText);
            linearLayout.addView(topWrapper);
            topWrapper.addView(viajantesWrapper);
            viajantesWrapper.addView(viajantesText);
            viajantesWrapper.addView(viajantesValue);
            topWrapper.addView(precoPessoaWrapper);
            precoPessoaWrapper.addView(precoPessoaText);
            precoPessoaWrapper.addView(precoPessoaValue);
            topWrapper.addView(configBtn);
            linearLayout.addView(bottomWrapper);
            bottomWrapper.addView(duracaoWrapper);
            duracaoWrapper.addView(duracaoText);
            duracaoWrapper.addView(duracaoValue);
            bottomWrapper.addView(precoTotalWrapper);
            precoTotalWrapper.addView(precoTotalText);
            precoTotalWrapper.addView(precoTotalValue);
            bottomWrapper.addView(deleteBtn);

        }

    }

    public static float calculoTotal(Boolean hospedagem, Boolean refeicoes, Boolean tarifaAerea, Boolean combustivel,
                              float custoMedioNoiteValue, Integer totalNoitesValue, Integer totalQuartosValue,
                              Integer refeicoesDiaValue, Integer numeroViajantesValue, float custoEstimadoRefeicaoValue,
                              Integer duracaoDiasValue, float custoEstimadoPessoaValue, float aluguelVeiculoValue,
                              float mediaQuilometrosLitroValue, Integer totalVeiculosValue, float totalEstimadoQuilometrosValue,
                              float custoMedioLitroValue, List<Entreteinment> entretenimentoValues){
        float result = 0;
        if(hospedagem){
            result += (custoMedioNoiteValue*totalNoitesValue)*totalQuartosValue;
        }
        if(refeicoes){
            result += ((refeicoesDiaValue*numeroViajantesValue)*custoEstimadoRefeicaoValue)*duracaoDiasValue;
        }
        if(tarifaAerea){
            result += (custoEstimadoPessoaValue*numeroViajantesValue) + aluguelVeiculoValue;
        }

        if(combustivel && mediaQuilometrosLitroValue!=0 && totalVeiculosValue!=0){
            result += ((totalEstimadoQuilometrosValue/mediaQuilometrosLitroValue)*custoMedioLitroValue)/totalVeiculosValue;
        }
        for (Entreteinment current: entretenimentoValues) {
            result+=current.getPrice();
        }
        return result;
    }
}
