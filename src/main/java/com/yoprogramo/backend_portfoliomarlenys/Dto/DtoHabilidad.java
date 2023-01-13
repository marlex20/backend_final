package com.yoprogramo.backend_portfoliomarlenys.Dto;

import javax.validation.constraints.NotBlank;

public class DtoHabilidad {

    @NotBlank
    private String name;
    @NotBlank
    private int val;

    //Constructores
    public DtoHabilidad() {
    }

    public DtoHabilidad(String name, int val) {
        this.name = name;
        this.val = val;
    }

    //Getter y Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

}
