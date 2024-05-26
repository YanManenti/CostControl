package com.example.costcontrol.Models;

import java.util.List;

public class TripModel {
    public String id;
    public String destino;
    public Number numeroViajantes;
    public Number duracaoDias;
    public boolean combustivel;
    public float totalEstimadoQuilometros;
    public float mediaQuilometrosLitro;
    public float custoMedioLitro;
    public int totalVeiculos;
    public boolean tarifaAerea;
    public float custoEstimadoPessoa;
    public float aluguelVeiculo;
    public boolean refeicoes;
    public float custoEstimadoRefeicao;
    public int refeicoesDia;
    public boolean hospedagem;
    public float custoMedioNoite;
    public int totalNoites;
    public int totalQuartos;
    public List<EntretenimentoModel> entretenimento;
}
