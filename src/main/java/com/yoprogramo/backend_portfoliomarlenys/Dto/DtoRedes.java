
package com.yoprogramo.backend_portfoliomarlenys.Dto;

import javax.validation.constraints.NotBlank;

public class DtoRedes {
    @NotBlank
    private String name;
    @NotBlank
    private String logo;

    //Constructores
    public DtoRedes() {
    }

    public DtoRedes(String name, String logo) {
        this.name = name;
        this.logo = logo;
    }
    
    //Getter y Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }    
}
