package com.farmacia.ApiFarmacia.Model.UnidadeFarmacia;

public class UnidadeFarmacia {
    String nomeFarmacia;
    String rua_avenida;

    public UnidadeFarmacia(String nomeFarmacia, String rua_avenida) {
        this.nomeFarmacia = nomeFarmacia;
        this.rua_avenida = rua_avenida;
    }

    public String getNomeFarmacia() {
        return nomeFarmacia;
    }

    public String getRua_avenida() {
        return rua_avenida;
    }
}
