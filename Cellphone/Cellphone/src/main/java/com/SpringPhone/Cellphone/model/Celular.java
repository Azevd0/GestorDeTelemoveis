package com.SpringPhone.Cellphone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
@Table(name = "celular")
@Entity
public class Celular {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String modelo;
    private BigDecimal preco;
    private Integer ano;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "marca_id")
    private Marca marca;
    private Integer qtd;

    public Celular() {
    }

    public Celular(String modelo,BigDecimal preco, Integer ano, Marca marca, Integer qtd) {
        this.modelo = modelo;
        this.preco = preco;
        this.ano = ano;
        this.marca = marca;
        this.qtd = qtd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Celular celular = (Celular) o;
        return Objects.equals(id, celular.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
