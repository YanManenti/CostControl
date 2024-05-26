package com.example.costcontrol.persistance.models;

public class Trip {
    public Integer id;
    public String destino;
    public Integer numeroViajantes;
    public Integer duracaoDias;
    public Boolean combustivel;
    public Float totalEstimadoQuilometros;
    public Float mediaQuilometrosLitro;
    public Float custoMedioLitro;
    public Integer totalVeiculos;
    public Float custoMedioNoite;
    public Integer totalNoites;
    public Integer totalQuartos;
    public Integer userId;
    public Boolean tarifaAerea;
    public Float custoEstimadoPessoa;
    public Float aluguelVeiculo;
    public Boolean refeicoes;
    public Float custoEstimadoRefeicao;
    public Integer refeicoesDia;
    public Boolean hospedagem;

    public Trip() {
    }

    public Trip(Integer id, String destino, Integer numeroViajantes, Integer duracaoDias,
                Boolean combustivel, Float totalEstimadoQuilometros, Float mediaQuilometrosLitro,
                Float custoMedioLitro, Integer totalVeiculos, Float custoMedioNoite,
                Integer totalNoites, Integer totalQuartos, Integer userId, Boolean tarifaAerea,
                Float custoEstimadoPessoa, Float aluguelVeiculo, Boolean refeicoes,
                Float custoEstimadoRefeicao, Integer refeicoesDia, Boolean hospedagem) {

        this.id = id;
        this.destino = destino;
        this.numeroViajantes = numeroViajantes;
        this.duracaoDias = duracaoDias;
        this.combustivel = combustivel;
        this.totalEstimadoQuilometros = totalEstimadoQuilometros;
        this.mediaQuilometrosLitro = mediaQuilometrosLitro;
        this.custoMedioLitro = custoMedioLitro;
        this.totalVeiculos = totalVeiculos;
        this.custoMedioNoite = custoMedioNoite;
        this.totalNoites = totalNoites;
        this.totalQuartos = totalQuartos;
        this.userId = userId;
        this.tarifaAerea = tarifaAerea;
        this.custoEstimadoPessoa = custoEstimadoPessoa;
        this.aluguelVeiculo = aluguelVeiculo;
        this.refeicoes = refeicoes;
        this.custoEstimadoRefeicao = custoEstimadoRefeicao;
        this.refeicoesDia = refeicoesDia;
        this.hospedagem = hospedagem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Integer getNumeroViajantes() {
        return numeroViajantes;
    }

    public void setNumeroViajantes(Integer numeroViajantes) {
        this.numeroViajantes = numeroViajantes;
    }

    public Integer getDuracaoDias() {
        return duracaoDias;
    }

    public void setDuracaoDias(Integer duracaoDias) {
        this.duracaoDias = duracaoDias;
    }

    public Boolean getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(Boolean combustivel) {
        this.combustivel = combustivel;
    }

    public Float getTotalEstimadoQuilometros() {
        return totalEstimadoQuilometros;
    }

    public void setTotalEstimadoQuilometros(Float totalEstimadoQuilometros) {
        this.totalEstimadoQuilometros = totalEstimadoQuilometros;
    }

    public Float getMediaQuilometrosLitro() {
        return mediaQuilometrosLitro;
    }

    public void setMediaQuilometrosLitro(Float mediaQuilometrosLitro) {
        this.mediaQuilometrosLitro = mediaQuilometrosLitro;
    }

    public Float getCustoMedioLitro() {
        return custoMedioLitro;
    }

    public void setCustoMedioLitro(Float custoMedioLitro) {
        this.custoMedioLitro = custoMedioLitro;
    }

    public Integer getTotalVeiculos() {
        return totalVeiculos;
    }

    public void setTotalVeiculos(Integer totalVeiculos) {
        this.totalVeiculos = totalVeiculos;
    }

    public Float getCustoMedioNoite() {
        return custoMedioNoite;
    }

    public void setCustoMedioNoite(Float custoMedioNoite) {
        this.custoMedioNoite = custoMedioNoite;
    }

    public Integer getTotalNoites() {
        return totalNoites;
    }

    public void setTotalNoites(Integer totalNoites) {
        this.totalNoites = totalNoites;
    }

    public Integer getTotalQuartos() {
        return totalQuartos;
    }

    public void setTotalQuartos(Integer totalQuartos) {
        this.totalQuartos = totalQuartos;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getTarifaAerea() {
        return tarifaAerea;
    }

    public void setTarifaAerea(Boolean tarifaAerea) {
        this.tarifaAerea = tarifaAerea;
    }

    public Float getCustoEstimadoPessoa() {
        return custoEstimadoPessoa;
    }

    public void setCustoEstimadoPessoa(Float custoEstimadoPessoa) {
        this.custoEstimadoPessoa = custoEstimadoPessoa;
    }

    public Float getAluguelVeiculo() {
        return aluguelVeiculo;
    }

    public void setAluguelVeiculo(Float aluguelVeiculo) {
        this.aluguelVeiculo = aluguelVeiculo;
    }

    public Boolean getRefeicoes() {
        return refeicoes;
    }

    public void setRefeicoes(Boolean refeicoes) {
        this.refeicoes = refeicoes;
    }

    public Float getCustoEstimadoRefeicao() {
        return custoEstimadoRefeicao;
    }

    public void setCustoEstimadoRefeicao(Float custoEstimadoRefeicao) {
        this.custoEstimadoRefeicao = custoEstimadoRefeicao;
    }

    public Integer getRefeicoesDia() {
        return refeicoesDia;
    }

    public void setRefeicoesDia(Integer refeicoesDia) {
        this.refeicoesDia = refeicoesDia;
    }

    public Boolean getHospedagem() {
        return hospedagem;
    }

    public void setHospedagem(Boolean hospedagem) {
        this.hospedagem = hospedagem;
    }
}