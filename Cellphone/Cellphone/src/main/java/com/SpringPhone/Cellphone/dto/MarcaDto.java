package com.SpringPhone.Cellphone.dto;

import com.SpringPhone.Cellphone.model.Celular;
import com.SpringPhone.Cellphone.model.Marca;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class MarcaDto {
    private Long id;
    @NotNull
    @Length(min = 5, max = 20, message = "O campo nome deve ter entre 5 e 20 caracteres")
    private String nome;
    private List<Celular> celulares = new ArrayList<>();

    public MarcaDto(){
    }

    public MarcaDto(Marca marca) {
        this.id = marca.getId();
        this.nome = marca.getNome();
        this.celulares = marca.getCelulares();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Celular> getCelulares() {
        return celulares;
    }

    public void setCelulares(List<Celular> celulares) {
        this.celulares = celulares;
    }
}
