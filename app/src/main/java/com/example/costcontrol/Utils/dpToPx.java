package com.example.costcontrol.Utils;

import android.content.res.Resources;

public class dpToPx {
    public static int convert(Resources resources, float dp) {
        return Math.round(dp * resources.getDisplayMetrics().density);
    }
}
