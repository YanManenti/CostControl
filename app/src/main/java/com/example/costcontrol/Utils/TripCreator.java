package com.example.costcontrol.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
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
import com.example.costcontrol.R;

import java.util.List;

public class TripCreator {
    Resources resources;

    public int dpToPx(Context context, float dp) {
        return Math.round(dp * resources.getDisplayMetrics().density);
    }

    @SuppressLint("SetTextI18n")
    public TripCreator(LinearLayout basicView, Context context, List<TripModel> list) {
        resources = basicView.getResources();
        int dp;
        for (TripModel trip :  list) {

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, 0, 5);
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setBackgroundResource(R.drawable.customyellowbutton);
            linearLayout.setBaselineAligned(false);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            dp = dpToPx(context, 15);
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
            precoPessoaValue.setText("R$ "+trip.custoEstimadoPessoa);
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
                Toast.makeText(v.getContext(), "configBtn CLICK", Toast.LENGTH_LONG).show();
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
            precoTotalValue.setText("R$ "+"000");
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
                //deletes from the database then reloades page
                Toast.makeText(v.getContext(), "deleteBtn CLICK", Toast.LENGTH_LONG).show();
                getActivity(context).recreate();//??????????????????????????????????????????????
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

    public Activity getActivity(Context context)
    {
        if (context == null)
        {
            return null;
        }
        else if (context instanceof ContextWrapper)
        {
            if (context instanceof Activity)
            {
                return (Activity) context;
            }
            else
            {
                return getActivity(((ContextWrapper) context).getBaseContext());
            }
        }

        return null;
    }
}