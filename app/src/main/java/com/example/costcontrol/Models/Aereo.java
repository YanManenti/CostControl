package com.example.costcontrol.Models;

import java.io.Serializable;

public class Aereo implements Serializable {
    public int usuario;
    public double custoPessoa;
    public double custoAluguelVeiculo;

    public Aereo(int usuario, double custoPessoa, double custoAluguelVeiculo) {
        this.usuario = usuario;
        this.custoPessoa = custoPessoa;
        this.custoAluguelVeiculo = custoAluguelVeiculo;
    }
}
