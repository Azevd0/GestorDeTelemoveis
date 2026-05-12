package com.SpringPhone.Cellphone.model.purchase;

public class CompraRequest {
    private Long celularId;
    private Long marcaId;
    private Integer quantidade;

    public Long getCelularId() {
        return celularId;
    }

    public void setCelularId(Long celularId) {
        this.celularId = celularId;
    }

    public Long getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Long marcaId) {
        this.marcaId = marcaId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
