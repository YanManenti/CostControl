package com.example.costcontrol.Models;

import java.io.Serializable;
import java.math.BigDecimal;

public class EntretenimentoModel implements Serializable {
    public int usuario;
    public String entretenimento;
    public double valor;

    public EntretenimentoModel(int usuario, String entretenimento, double valor) {
        this.usuario = usuario;
        this.entretenimento = entretenimento;
        this.valor = valor;
    }
}
