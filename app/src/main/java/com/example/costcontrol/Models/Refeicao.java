package com.example.costcontrol.Models;

import java.io.Serializable;

public class Refeicao implements Serializable {
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
    public double custoRefeicao;
    public int refeicoesDia;

    public Refeicao(long id, long viagemId, double custoRefeicao, int refeicoesDia) {
        this.id = id;
        this.viagemId = viagemId;
        this.custoRefeicao = custoRefeicao;
        this.refeicoesDia = refeicoesDia;
    }
}
