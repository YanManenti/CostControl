package com.example.costcontrol.Models;

import java.io.Serializable;
import java.math.BigDecimal;

public class EntretenimentoModel implements Serializable {
    public String nome;
    public float preco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
}
