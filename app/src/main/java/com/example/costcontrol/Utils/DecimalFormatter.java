package com.example.costcontrol.Utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class DecimalFormatter {
    public static String format(double value){
        NumberFormat formatter = new DecimalFormat("#0.00");
        return formatter.format(value);
    }
}
