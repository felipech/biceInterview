package com.entrevista.bicevida.dto;

public class Copago {
    private Integer nroWorker;
    private Double copago;

    public Copago(){
        this.copago = 0.0;
    }

    public Double getCopago() {
        return copago;
    }

    public void setCopago(Double copago) {
        this.copago = copago;
    }

    public Integer getNroWorker() {
        return nroWorker;
    }

    public void setNroWorker(Integer nroWorker) {
        this.nroWorker = nroWorker;
    }
}
