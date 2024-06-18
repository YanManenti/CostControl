package com.example.costcontrol.Models;

import java.io.Serializable;
import java.math.BigDecimal;

public class EntretenimentoModel implements Serializable {
    private long id;
    private long viagemId;

    public EntretenimentoModel() {

    }

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
    public String entretenimento;
    public double valor;

    public EntretenimentoModel(long id, long viagemId, String entretenimento, double valor) {
        this.id = id;
        this.viagemId = viagemId;
        this.entretenimento = entretenimento;
        this.valor = valor;
    }
}
