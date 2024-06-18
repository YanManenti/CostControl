package com.example.costcontrol.Models;

import java.io.Serializable;

public class Gasolina implements Serializable {
    public int usuario;
    public int totalEstimadoKM;
    public double mediaKMLitro;
    public double custoMedioLitro;
    public int totalVeiculos;

    public Gasolina(int usuario, int totalEstimadoKM, double mediaKMLitro, double custoMedioLitro, int totalVeiculos) {
        this.usuario = usuario;
        this.totalEstimadoKM = totalEstimadoKM;
        this.mediaKMLitro = mediaKMLitro;
        this.custoMedioLitro = custoMedioLitro;
        this.totalVeiculos = totalVeiculos;
    }
}
