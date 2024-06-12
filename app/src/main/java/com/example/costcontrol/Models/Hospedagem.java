package com.example.costcontrol.Models;

import java.io.Serializable;

public class Hospedagem implements Serializable {
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
    public double custoMedioNoite;
    public int totalNoite;
    public int totalQuartos;

    public Hospedagem(long id, long viagemId, double custoMedioNoite, int totalNoite, int totalQuartos) {
        this.id = id;
        this.viagemId = viagemId;
        this.custoMedioNoite = custoMedioNoite;
        this.totalNoite = totalNoite;
        this.totalQuartos = totalQuartos;
    }
}
