package com.example.costcontrol.Models;

import java.io.Serializable;

public class Gasolina implements Serializable {
    private long id;
    private long viagemId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getViagemId() {
        return viagemId;
    }

    public void setViagemId(long viagemId) {
        this.viagemId = viagemId;
    }
    private final long idConta = 128113;
    public int totalEstimadoKm;
    public double mediaKMLitro;
    public double custoMedioLitro;
    public int totalVeiculos;

    public Gasolina(long id, long viagemId, int totalEstimadoKm, double mediaKMLitro, double custoMedioLitro, int totalVeiculos) {
        this.id = id;
        this.viagemId = viagemId;
        this.totalEstimadoKm = totalEstimadoKm;
        this.mediaKMLitro = mediaKMLitro;
        this.custoMedioLitro = custoMedioLitro;
        this.totalVeiculos = totalVeiculos;
    }
}
