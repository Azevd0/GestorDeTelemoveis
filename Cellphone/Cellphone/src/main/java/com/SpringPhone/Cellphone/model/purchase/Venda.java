package com.SpringPhone.Cellphone.model.purchase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "venda")
public class Venda {
    @Id
    @GeneratedValue
    private UUID id;
    private LocalDateTime dataCompra = LocalDateTime.now();
    private String modelo;
    private Integer quantidadeComprada;
    private BigDecimal valorTotal;

    public Venda(){
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getQuantidadeComprada() {
        return quantidadeComprada;
    }

    public void setQuantidadeComprada(Integer quantidadeComprada) {
        this.quantidadeComprada = quantidadeComprada;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Venda(UUID uuid, LocalDateTime dataCompra, String modelo, Integer quantidadeComprada, BigDecimal valorTotal) {
        this.id = uuid;
        this.dataCompra = dataCompra;
        this.modelo = modelo;
        this.quantidadeComprada = quantidadeComprada;
        this.valorTotal = valorTotal;


    }
}
