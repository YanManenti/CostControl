package com.example.costcontrol.Models;

import java.io.Serializable;
import java.util.List;

public class TripModel implements Serializable {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Number getNumeroViajantes() {
        return numeroViajantes;
    }

    public void setNumeroViajantes(Number numeroViajantes) {
        this.numeroViajantes = numeroViajantes;
    }

    public Number getDuracaoDias() {
        return duracaoDias;
    }

    public void setDuracaoDias(Number duracaoDias) {
        this.duracaoDias = duracaoDias;
    }

    public boolean isCombustivel() {
        return combustivel;
    }

    public void setCombustivel(boolean combustivel) {
        this.combustivel = combustivel;
    }

    public float getTotalEstimadoQuilometros() {
        return totalEstimadoQuilometros;
    }

    public void setTotalEstimadoQuilometros(float totalEstimadoQuilometros) {
        this.totalEstimadoQuilometros = totalEstimadoQuilometros;
    }

    public float getMediaQuilometrosLitro() {
        return mediaQuilometrosLitro;
    }

    public void setMediaQuilometrosLitro(float mediaQuilometrosLitro) {
        this.mediaQuilometrosLitro = mediaQuilometrosLitro;
    }

    public float getCustoMedioLitro() {
        return custoMedioLitro;
    }

    public void setCustoMedioLitro(float custoMedioLitro) {
        this.custoMedioLitro = custoMedioLitro;
    }

    public int getTotalVeiculos() {
        return totalVeiculos;
    }

    public void setTotalVeiculos(int totalVeiculos) {
        this.totalVeiculos = totalVeiculos;
    }

    public boolean isTarifaAerea() {
        return tarifaAerea;
    }

    public void setTarifaAerea(boolean tarifaAerea) {
        this.tarifaAerea = tarifaAerea;
    }

    public float getCustoEstimadoPessoa() {
        return custoEstimadoPessoa;
    }

    public void setCustoEstimadoPessoa(float custoEstimadoPessoa) {
        this.custoEstimadoPessoa = custoEstimadoPessoa;
    }

    public float getAluguelVeiculo() {
        return aluguelVeiculo;
    }

    public void setAluguelVeiculo(float aluguelVeiculo) {
        this.aluguelVeiculo = aluguelVeiculo;
    }

    public boolean isRefeicoes() {
        return refeicoes;
    }

    public void setRefeicoes(boolean refeicoes) {
        this.refeicoes = refeicoes;
    }

    public float getCustoEstimadoRefeicao() {
        return custoEstimadoRefeicao;
    }

    public void setCustoEstimadoRefeicao(float custoEstimadoRefeicao) {
        this.custoEstimadoRefeicao = custoEstimadoRefeicao;
    }

    public int getRefeicoesDia() {
        return refeicoesDia;
    }

    public void setRefeicoesDia(int refeicoesDia) {
        this.refeicoesDia = refeicoesDia;
    }

    public boolean isHospedagem() {
        return hospedagem;
    }

    public void setHospedagem(boolean hospedagem) {
        this.hospedagem = hospedagem;
    }

    public float getCustoMedioNoite() {
        return custoMedioNoite;
    }

    public void setCustoMedioNoite(float custoMedioNoite) {
        this.custoMedioNoite = custoMedioNoite;
    }

    public int getTotalNoites() {
        return totalNoites;
    }

    public void setTotalNoites(int totalNoites) {
        this.totalNoites = totalNoites;
    }

    public int getTotalQuartos() {
        return totalQuartos;
    }

    public void setTotalQuartos(int totalQuartos) {
        this.totalQuartos = totalQuartos;
    }

    public List<EntretenimentoModel> getEntretenimento() {
        return entretenimento;
    }

    public void setEntretenimento(List<EntretenimentoModel> entretenimento) {
        this.entretenimento = entretenimento;
    }

    public boolean hospedagem;
    public float custoMedioNoite;
    public int totalNoites;
    public int totalQuartos;
    public List<EntretenimentoModel> entretenimento;
}
