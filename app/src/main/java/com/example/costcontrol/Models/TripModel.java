package com.example.costcontrol.Models;

import java.io.Serializable;
import java.util.List;

public class TripModel implements Serializable {
    private long id;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    private final long idConta = 128113;
    public int totalViajantes;
    public int duracaoViagem;
    public double custoTotalViagem;
    public double custoPorPessoa;
    public String local;
    public Gasolina gasolina;
    public Aereo aereo;
    public Hospedagem hospedagem;
    public Refeicao refeicao;
    public List<EntretenimentoModel> listaEntretenimento;

    public long getIdConta() {
        return idConta;
    }

    public TripModel(long id, int totalViajantes, int duracaoViagem, double custoTotalViagem, double custoPorPessoa, String local, Gasolina gasolina, Aereo aereo, Hospedagem hospedagem, Refeicao refeicao, List<EntretenimentoModel> listaEntretenimento) {
        this.id = id;
        this.totalViajantes = totalViajantes;
        this.duracaoViagem = duracaoViagem;
        this.custoTotalViagem = custoTotalViagem;
        this.custoPorPessoa = custoPorPessoa;
        this.local = local;
        this.gasolina = gasolina;
        this.aereo = aereo;
        this.hospedagem = hospedagem;
        this.refeicao = refeicao;
        this.listaEntretenimento = listaEntretenimento;
    }
}
