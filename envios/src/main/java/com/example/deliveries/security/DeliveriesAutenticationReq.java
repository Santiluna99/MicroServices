package com.example.deliveries.security;

import java.io.Serializable;

public class DeliveriesAutenticationReq implements Serializable{
    private static final long serialVersionUID = 1L;

    private String usuario;
    private String clave;

    //CONSTRUCTOR PARA DESERIALIZACION JSON
    public DeliveriesAutenticationReq() {
    }

    public DeliveriesAutenticationReq(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}