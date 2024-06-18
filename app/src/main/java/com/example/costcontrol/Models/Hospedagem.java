package com.example.costcontrol.Models;

import java.io.Serializable;

public class Hospedagem implements Serializable {
    public int usuario;
    public double custoMedioNoite;
    public int totalNoite;
    public int totalQuartos;

    public Hospedagem(int usuario, double custoMedioNoite, int totalNoite, int totalQuartos) {
        this.usuario = usuario;
        this.custoMedioNoite = custoMedioNoite;
        this.totalNoite = totalNoite;
        this.totalQuartos = totalQuartos;
    }
}
