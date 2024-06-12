package com.example.costcontrol.Models;

import java.io.Serializable;

public class Aereo implements Serializable {
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
    public double custoPessoa;
    public double custoAluguelVeiculo;

    public Aereo(long id, long viagemId, double custoPessoa, double custoAluguelVeiculo) {
        this.id = id;
        this.viagemId = viagemId;
        this.custoPessoa = custoPessoa;
        this.custoAluguelVeiculo = custoAluguelVeiculo;
    }
}
