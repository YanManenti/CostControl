package com.example.costcontrol.Models;

import java.io.Serializable;

public class Refeicao implements Serializable {
    public int usuario;
    public double custoRefeicao;
    public int refeicoesDia;

    public Refeicao(int usuario, double custoRefeicao, int refeicoesDia) {
        this.usuario = usuario;
        this.custoRefeicao = custoRefeicao;
        this.refeicoesDia = refeicoesDia;
    }
}
