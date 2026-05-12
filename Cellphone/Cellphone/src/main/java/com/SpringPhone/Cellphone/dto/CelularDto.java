package com.SpringPhone.Cellphone.dto;

import com.SpringPhone.Cellphone.model.Marca;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


public class CelularDto {
    private Long id;

    @NotNull
    @Length(min = 5, max = 20, message = "O campo modelo dete ter entre 5 e 20 caracteres")
    private String modelo;
    @NotNull(message = "O celular deve ter um preço")
    private BigDecimal preco;
    @NotNull(message = "O ano é um campo obrigatório")
    private Integer ano;
    private Marca marca;
    private Integer quantidade;

    public CelularDto(){
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

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
