package com.entrevista.bicevida.dto;

import java.util.List;

public class ResultPolicy {
    private Double valorPolizaEmpresa = 0.0;
    private List<Copago> listaCopagoPorWorker;

    public ResultPolicy(){};

    public Double getValorPolizaEmpresa() {
        return valorPolizaEmpresa;
    }

    public void setValorPolizaEmpresa(Double valorPolizaEmpresa) {
        this.valorPolizaEmpresa = valorPolizaEmpresa;
    }

    public List<Copago> getListaCopagoPorWorker() {
        return listaCopagoPorWorker;
    }

    public void setListaCopagoPorWorker(List<Copago> listaCopagoPorWorker) {
        this.listaCopagoPorWorker = listaCopagoPorWorker;
    }
}
