package org.example;

public enum CupomDesconto {
    NENHUM(0.00),
    CUSTOFRETE(15.00),
    FRETEGRATIS(15.00),
    DESCONTO15(15.00),
    DESCONTO20(20.00),
    DESCONTO50(50.00),
    DESCONTO70(70.00);

    private Double valorDoCupom;
    CupomDesconto(double valorDoCupom) {
        this.valorDoCupom = valorDoCupom;
    }


    public Double getValorDoCupom() {
        return valorDoCupom;
    }
}
