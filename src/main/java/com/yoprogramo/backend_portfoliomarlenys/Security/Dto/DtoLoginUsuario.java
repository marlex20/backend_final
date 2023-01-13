package com.yoprogramo.backend_portfoliomarlenys.Security.Dto;

import javax.validation.constraints.NotBlank;

public class DtoLoginUsuario {

    @NotBlank
    private String nombreUsuario;
    @NotBlank
    private String password;

    //Getter y Setter
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
